package com.defa.slack.job;

import com.defa.slack.api.Methods;
import com.defa.slack.rtm.Connector;

public class TaskContext implements Context {
    private Connector connector;
    private Methods methods;

    public TaskContext(Connector connector, Methods methods) {
        assert null != connector && null != methods;
        this.connector = connector;
        this.methods = methods;
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
