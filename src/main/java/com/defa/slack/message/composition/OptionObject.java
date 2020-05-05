package com.defa.slack.message.composition;

public class OptionObject implements CompositionObject {
    private PlainTextObject text;
    private String value;

    public OptionObject(){
        this.text = new PlainTextObject();
        this.value = "";
    }


    public PlainTextObject getText() {
        return text;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
