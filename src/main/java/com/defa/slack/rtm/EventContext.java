package com.defa.slack.rtm;

import com.defa.slack.api.Methods;
import com.defa.slack.rtm.event.Context;
import com.defa.slack.rtm.event.Event;

public class EventContext<T> implements Context<T> {
    private Event<T> event;
    private Methods methods;

    public EventContext(Event<T> event, Methods methods) {
        assert null != event && null != methods;
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
}
