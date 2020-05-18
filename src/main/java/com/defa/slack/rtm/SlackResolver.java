package com.defa.slack.rtm;

import com.defa.slack.api.Methods;
import com.defa.slack.job.CronTask;
import com.defa.slack.job.SlackJob;
import com.defa.slack.job.Task;
import com.defa.slack.job.TaskContext;
import com.defa.slack.rtm.event.Handler;
import com.defa.slack.rtm.event.Parser;
import com.defa.slack.rtm.event.RTMEvent;
import com.defa.slack.rtm.event.TypeEvent;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.*;

public class SlackResolver<T> implements Resolver, Slack<T> {
    private final Parser<T> parser;
    private final Map<String, Handler<T>> handlers;
    private final List<CronTask> cronTasks;

    public SlackResolver(Parser<T> parser){
        assert parser != null;
        this.parser = parser;
        this.handlers = new HashMap<>();
        this.cronTasks = new ArrayList<>();
    }

    private Parser<T> getParser() {
        return this.parser;
    }

    private Map<String, Handler<T>> getHandlers() {
        return this.handlers;
    }

    //listen to a RTM event.
    public SlackResolver<T> listen(RTMEvent e, Handler<T> handler) {
        assert e != null;
        this.getHandlers().put(e.toString(), handler);
        return this;
    }

    //setup an cron job
    public SlackResolver<T> schedule(String cron, Task handler){
        assert  null != cron && null != handler;
        this.cronTasks.add(new CronTask(cron, handler));
        return this;
    }

    @Override
    public void resolve(final Connector connector, final Methods methods, final String message) {
        Map<String, Handler<T>> hdl = this.handlers;
        TypeEvent<T> e = this.getParser().parse(message);
        if(e!=null && hdl.containsKey(e.getType())){
            EventContext context = new EventContext(connector, e.getEvent(), methods);
            hdl.get(e.getType()).handle(context);
        }
    }

    @Override
    public void cron(final Connector connector, final Methods methods){
        if(this.cronTasks.size()<1) return;
        TaskContext context = new TaskContext(connector, methods);
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();
            int i = 0;
            for(CronTask pair: this.cronTasks){
                i++;
                String cron = pair.getCron();
                Task handler = pair.getTask();
                JobDataMap jdm = new JobDataMap();
                //ugly implement
                jdm.put(Task.HANDLER_STORE_KEY, handler);
                jdm.put(Task.HANDLER_STORE_CONTEXT, context);
                String jobId = "job" + i, groupId = "group" + i, triggerId = "trigger"+ i;
                JobDetail jb = JobBuilder.newJob(SlackJob.class)
                        .withIdentity(jobId, groupId)
                        .setJobData(jdm)
                        .build();
                Trigger tg = TriggerBuilder.newTrigger()
                        .withIdentity(triggerId, groupId)
                        .startAt(new Date(System.currentTimeMillis() + 1000L))
                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                        .build();
                scheduler.scheduleJob(jb, tg);
            }
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
