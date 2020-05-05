package com.defa.slack.message.builder;

import com.defa.slack.message.Markdown;
import com.defa.slack.message.composition.MarkdownTextObject;

public final class MarkdownTextObjectBuilder {
    private String text;
    private boolean verbatim;

    private MarkdownTextObjectBuilder() {}

    public static MarkdownTextObjectBuilder builder() {
        return new MarkdownTextObjectBuilder();
    }

    public MarkdownTextObjectBuilder text(final String text) {
        this.text = text;
        return this;
    }

    public MarkdownTextObjectBuilder text(final Markdown markdown) {
        return text(markdown.format());
    }

    public MarkdownTextObjectBuilder verbatim(boolean verbatim) {
        this.verbatim = verbatim;
        return this;
    }

    public MarkdownTextObject build() {
        MarkdownTextObject txt = new MarkdownTextObject();
        if(!(text == null || text.length() ==0)) txt.setText(text);
        txt.setVerbatim(verbatim);
        return txt;
    }
}
