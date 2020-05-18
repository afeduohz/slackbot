package com.defa.slack.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

public final class SlackJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap jdm = jobExecutionContext.getMergedJobDataMap();
        if (null == jdm) return;
        Object task = jdm.get(Task.HANDLER_STORE_KEY);
        Object context = jdm.get(Task.HANDLER_STORE_CONTEXT);
        if(task instanceof Task && context instanceof Context){
            try{
                ((Task) task).execute((Context) context);
            }catch (TaskException e){
                e.printStackTrace();
            }
        }
    }

}
