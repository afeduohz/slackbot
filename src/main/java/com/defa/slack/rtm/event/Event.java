package com.defa.slack.rtm.event;

public interface Event<T> {
    T getEvent();
}
