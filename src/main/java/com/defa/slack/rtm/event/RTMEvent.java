package com.defa.slack.rtm.event;

public enum RTMEvent {
    HELLO("hello"),
    GOODBYE("goodbye"),
    MESSAGE("message"),
    MESSAGE_BOT_MESSAGE("message+bot_message"),
    MESSAGE_CHANNEL_JOIN("message+channel_join"),

    MEMBER_JOIN_CHANNEL("member_joined_channel");

    String event;
    RTMEvent(String e){
        event = e;
    }

    public static RTMEvent ofType(String type){
        for(RTMEvent e: RTMEvent.values()) {
            if(e.toString().equals(type)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return event;
    }

}
