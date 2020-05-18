package com.defa.slack.rtm;

public interface Connector {
    boolean connected();
    void connect();
}
