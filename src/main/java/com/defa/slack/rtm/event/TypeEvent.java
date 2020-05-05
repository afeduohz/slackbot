package com.defa.slack.rtm.event;


public class TypeEvent<T> {
    private final String type;
    private final Event<T> event;

    public TypeEvent(String type, Event<T> event){
        assert null!=type && !"".equals(type);
        assert null!=event;
        this.type = type;
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public Event<T> getEvent() {
        return event;
    }
}
