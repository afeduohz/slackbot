package com.defa.slack.message.block.element;

import com.fasterxml.jackson.annotation.JsonValue;

public class AbstractElement implements Element {
    public enum Type {
        BUTTON("button"),
        DATEPICKER("datepicker"),
        IMAGE("image"),
        OVERFLOW("overflow"),

        STATIC_SELECT("static_select"),
        EXTERNAL_SELECT("external_select"),
        USERS_SELECT("users_select"),
        CONVERSATIONS_SELECT("conversations_select"),
        CHANNELS_SELECT("channels_select"),

        UNKNOWN("unknown");

        private String type;

        Type(String type){
            this.type = type;
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }

    private Type type;

    public AbstractElement(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
