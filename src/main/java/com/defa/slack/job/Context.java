package com.defa.slack.job;

import com.defa.slack.api.Methods;
import com.defa.slack.rtm.Connector;

public interface Context {
    Methods methods();
    Connector connector();
}
