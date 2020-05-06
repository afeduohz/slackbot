package com.defa.slack.job;

public interface Task {
    String HANDLER_STORE_KEY = "$$##JOB-HANDLER##$$";
    String HANDLER_STORE_CONTEXT = "$$##JOB-METHODS##$$";
    void execute(Context context) throws TaskException;
}
