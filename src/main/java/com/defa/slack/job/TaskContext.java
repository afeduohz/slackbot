package com.defa.slack.job;

import com.defa.slack.api.Methods;

import java.lang.annotation.Target;

public class TaskContext implements Context {
    private Methods methods;

    public TaskContext(Methods methods) {
        assert null != methods;
        this.methods = methods;
    }

    @Override
    public Methods methods() {
        return null;
    }
}
