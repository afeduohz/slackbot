package com.defa.slack.rtm.event;

import com.defa.slack.rtm.Context;

public interface Handler<T> {
    void handle(Context<T> context);
}
