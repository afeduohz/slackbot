package com.defa.slack.job;

public class CronTask {
    private final String cron;
    private final Task task;

    public CronTask(String cron, Task task){
        this.cron = cron;
        this.task = task;
    }

    public String getCron() {
        return cron;
    }

    public Task getTask() {
        return task;
    }
}
