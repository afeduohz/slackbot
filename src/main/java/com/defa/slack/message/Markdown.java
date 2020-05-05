package com.defa.slack.message;

import java.util.Date;

public class Markdown implements Formatter {

    private static final String ONE_SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final String EMAIL_EXP = ".+@.+\\..+";

    private static final String ITALIC_L = " _";
    private static final String ITALIC_R = "_";
    private static final String BOLD_L = " *";
    private static final String BOLD_R = "*";
    private static final String STRIKE_L = " ~";
    private static final String STRIKE_R = "~";
    private static final String TICK_L = " `";
    private static final String TICK_R = "`";
    private static final String TICK_3L = " ```";
    private static final String TICK_3R = "```";

    private static final String QUOTE = NEW_LINE + ">";
    private static final String LIST_DEFAULT = "• ";

    private static final String OBJECT_START_DEFAULT = "<";
    private static final String OBJECT_START_MAILTO = "<mailto:";
    private static final String OBJECT_START_USER = "<@";
    private static final String OBJECT_START_CHANNEL = "<#";
    private static final String OBJECT_START_GROUP = "<!subteam^";
    private static final String OBJECT_START_DATE = "<!date^";
    private static final String OBJECT_END = ">";

    private static final String HERE = "<!here>";
    private static final String CHANNEL = "<!channel>";
    private static final String EVERYONE = "<!everyone>";

    private static final String SYMBOL_ATTR = "^";
    private static final String SYMBOL_LABEL = "|";

    public interface More {
        void format(Markdown markdown);
    }


    private final StringBuilder cached;

    private Markdown(){
        this.cached = new StringBuilder();
    }

    private Markdown more(More m, String left, String right){
        Markdown more = Markdown.create();
        m.format(more);
        String inner = more.format();
        if(inner != null && inner.length() > 0) {
            if(null != left) cached.append(left);
            cached.append(inner);
            if(null != right) cached.append(right);
        }
        return this;
    }


    private String asOneLine(String stream) {
        return stream == null ? "":stream.replaceAll("\\n", "");
    }

    private void withLabel(String label) {
        if(label != null && label.length() > 0) {
            cached.append(SYMBOL_LABEL).append(label);
        }
    }

    public static Markdown create() {
        return new Markdown();
    }

    public Markdown escape(String stream) {
        if(stream != null) {
            cached.append(stream.replaceAll("&", "&amp;")
                    .replaceAll(OBJECT_START_DEFAULT, "&lt;")
            .replaceAll(OBJECT_END, "&gt;"));
        }
        return this;
    }

    public Markdown plain(String stream) {
        if(stream != null) {
            cached.append(stream);
        }
        return this;
    }

    public Markdown space() {
        return space(1);
    }

    public Markdown space(int i) {
        for(int j=0;j<i;j++) {
            cached.append(ONE_SPACE);
        }
        return this;
    }

    public Markdown s() {
        return space();
    }

    public Markdown newLine(){
        return newLine(1);
    }

    public Markdown newLine(int i){
        for(int j=0;j<i;j++) {
            cached.append(NEW_LINE);
        }
        return this;
    }

    public Markdown n() {
        return newLine();
    }

    public Markdown italic(String stream) {
        if(stream != null && stream.length() > 0) {
            cached.append(ITALIC_L).append(asOneLine(stream)).append(ITALIC_R);
        }
        return this;
    }

    public Markdown italic(More m){
        return more(m, ITALIC_L, ITALIC_R);
    }

    public Markdown bold(String stream) {
        if(stream != null && stream.length() > 0) {
            cached.append(BOLD_L).append(asOneLine(stream)).append(BOLD_R);
        }
        return this;
    }

    public Markdown bold(More m){
        return more(m, BOLD_L, BOLD_R);
    }

    public Markdown strikethrough(String stream) {
        if(stream != null && stream.length() > 0) {
            cached.append(STRIKE_L).append(asOneLine(stream)).append(STRIKE_R);
        }
        return this;
    }

    public Markdown strikethrough(More m) {
        return more(m, STRIKE_L, STRIKE_R);
    }

    public Markdown quote(){
        cached.append(QUOTE);
        return this;
    }

    public Markdown quote(String stream) {
        if(stream != null && stream.length() > 0) {
            cached.append(QUOTE).append(asOneLine(stream));
        }
        return this;
    }

    public Markdown quote(More m) {
        return more(m, QUOTE, null);
    }

    public Markdown inline(String stream) {
        if(stream != null && stream.length() > 0) {
            cached.append(TICK_L).append(asOneLine(stream)).append(TICK_R);
        }
        return this;
    }

    public Markdown multiline(String... streams) {
        String stream = String.join(NEW_LINE, streams);
        if(stream.length() > 0) {
            cached.append(TICK_3L).append(stream).append(TICK_3R);
        }
        return this;
    }

    public Markdown list(String stream) {
        return list(null, stream);
    }

    public Markdown list(More m) {
        return list(null, m);
    }

    public Markdown list(String li, String stream) {
        String dotted = (li == null)||"".equals(li.trim()) ? LIST_DEFAULT: li;
        cached.append(dotted).append(asOneLine(stream));
        return this.newLine();
    }

    public Markdown list(String li, More m) {
        Markdown more = Markdown.create();
        m.format(more);
        return list(li, more.format());
    }

    public Markdown link(String url) {
        if(url != null && url.length() > 0) {
            cached.append(OBJECT_START_DEFAULT).append(url).append(OBJECT_END);
        }
        return this;
    }

    public Markdown link(String url, String label) {
        if(url != null && url.length() > 0) {
            cached.append(OBJECT_START_DEFAULT).append(url);
            withLabel(label);
            cached.append(OBJECT_END);
        }
        return this;
    }

    public Markdown link(String url, More m) {
        Markdown markdown = new Markdown();
        m.format(markdown);
        return link(url, markdown.format());
    }

    public Markdown email(String address) {
        if(address != null && address.matches(EMAIL_EXP)) {
            cached.append(OBJECT_START_MAILTO).append(address).append(OBJECT_END);
        }
        return this;
    }

    public Markdown email(String address, String label) {
        if(address != null && address.matches(EMAIL_EXP)) {
            cached.append(OBJECT_START_MAILTO).append(address);
            withLabel(label);
            cached.append(OBJECT_END);
        }
        return this;
    }

    public Markdown email(String address, More m) {
        Markdown markdown = new Markdown();
        m.format(markdown);
        return email(address, markdown.format());
    }

    public Markdown channel(String chl) {
        if(null != chl) {
            cached.append(OBJECT_START_CHANNEL).append(chl).append(OBJECT_END);
        }
        return this;
    }

    public Markdown user(String who) {
        if(null != who) {
            cached.append(OBJECT_START_USER).append(who).append(OBJECT_END);
        }
        return this;
    }

    public Markdown group(String grp) {
        if(null != grp) {
            cached.append(OBJECT_START_GROUP).append(grp).append(OBJECT_END);
        }
        return this;
    }

    public Markdown here(){
        cached.append(HERE);
        return this;
    }

    public Markdown channel(){
        cached.append(CHANNEL);
        return this;
    }

    public Markdown everyone(){
        cached.append(EVERYONE);
        return this;
    }

    public Markdown time(long timestamp, String formal, String fallback){
        cached.append(OBJECT_START_DATE).append(timestamp).append(SYMBOL_ATTR).append(formal).append(SYMBOL_LABEL).append(fallback).append(OBJECT_END);
        return this;
    }

    public Markdown time(long timestamp, String formal, String url, String fallback){
        cached.append(OBJECT_START_DATE).append(timestamp)
                .append(SYMBOL_ATTR).append(formal)
                .append(SYMBOL_ATTR).append(url)
                .append(SYMBOL_LABEL).append(fallback)
                .append(OBJECT_END);
        return this;
    }

    public Markdown time(Date date, String formal, String fallback){
        return time(date.getTime()/1000, formal, fallback);
    }

    public Markdown time(Date date, String formal, String url, String fallback){
        return time(date.getTime()/1000, formal, url, fallback);
    }

    @Override
    public String toString(){
        return cached.toString();
    }


}
