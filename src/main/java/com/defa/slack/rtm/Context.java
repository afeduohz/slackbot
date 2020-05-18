package com.defa.slack.rtm;

import com.defa.slack.api.Methods;
import com.defa.slack.rtm.event.Event;

public interface Context<T> {
    Event<T> event();
    Methods methods();
    Connector connector();
}
