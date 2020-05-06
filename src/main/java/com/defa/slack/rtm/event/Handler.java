package com.defa.slack.rtm.event;

import com.defa.slack.api.Methods;

public interface Handler<T> {
    void handle(Context<T> context);
}
