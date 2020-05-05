package com.defa.slack.message.builder;

import com.defa.slack.message.composition.PlainTextObject;

public final class PlainTextObjectBuilder {
    private String text;
    private boolean emoji;

    private PlainTextObjectBuilder() {}

    public static PlainTextObjectBuilder builder() {
        return new PlainTextObjectBuilder();
    }

    public PlainTextObjectBuilder text(final String text) {
        this.text = text;
        return this;
    }

    public PlainTextObjectBuilder emoji(boolean emoji) {
        this.emoji = emoji;
        return this;
    }

    public PlainTextObject build() {
        PlainTextObject txt = new PlainTextObject();
        if(!(text == null || text.length() ==0)) txt.setText(text);
        txt.setEmoji(emoji);
        return txt;
    }
}
