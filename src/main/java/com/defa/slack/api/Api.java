package com.defa.slack.api;

public enum Api {

    RTM_CONNECT("rtm.connect"),

    CHAT_POST_MESSAGE("chat.postMessage"),
    CHAT_POST_EPHEMERAL("chat.postEphemeral"),
    CHAT_UPDATE("chat.update"),
    CHAT_DELETE("chat.delete"),

    PINS_ADD("pins.add"),
    PINS_REMOVE("pins.remove"),
    PINS_LIST("pins.list");

    private final String name;
    Api(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }

}
