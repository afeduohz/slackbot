package com.defa.slack.message.composition;

import com.defa.slack.message.block.ContextObject;
import com.fasterxml.jackson.annotation.JsonValue;

public abstract class AbstractTextObject implements CompositionObject, ContextObject {

    public enum Type {
        MARK_DOWN("mrkdwn"), PLAIN_TEXT("plain_text");

        private final String type;

        Type(String type){
            this.type = type;
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }

    private Type type;
    private String text;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
