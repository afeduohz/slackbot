package com.defa.slack.rtm.event;

public interface Parser<T> {
    TypeEvent<T> parse(String source);
}
