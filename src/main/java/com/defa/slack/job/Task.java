package com.defa.slack.job;

import com.defa.slack.api.Methods;

public interface Task {
    String HANDLER_STORE_KEY = "$$##JOB-HANDLER##$$";
    String HANDLER_STORE_METHODS = "$$##JOB-METHODS##$$";
    void execute(Methods methods) throws TaskException;
}
