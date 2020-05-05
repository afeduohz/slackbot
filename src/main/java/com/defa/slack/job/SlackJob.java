package com.defa.slack.job;

import com.defa.slack.api.Methods;
import org.quartz.*;

public final class SlackJob implements Job{
    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jdm = context.getMergedJobDataMap();
        if (null == jdm) return;
        Object task = jdm.get(Task.HANDLER_STORE_KEY);
        Object methods = jdm.get(Task.HANDLER_STORE_METHODS);
        if(task instanceof Task && methods instanceof Methods){
            try{
                ((Task) task).execute((Methods) methods);
            }catch (TaskException e){
                e.printStackTrace();
            }
        }
    }

}
