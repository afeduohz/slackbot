package com.defa.slack.rtm;

import com.defa.slack.api.Methods;
import com.defa.slack.rtm.event.Event;

public class EventContext<T> implements Context<T> {
    private Connector connector;
    private Event<T> event;
    private Methods methods;

    public EventContext(Connector connector, Event<T> event, Methods methods) {
        assert null != connector && null != event && null != methods;
        this.connector = connector;
        this.event = event;
        this.methods = methods;
    }

    @Override
    public Event<T> event() {
        return event;
    }

    @Override
    public Methods methods() {
        return methods;
    }

    @Override
    public Connector connector() {
        return connector;
    }
}
