package com.defa.slack.rtm;

import com.defa.slack.job.Task;
import com.defa.slack.rtm.event.Handler;
import com.defa.slack.rtm.event.RTMEvent;

public interface Slack<T> {
    Slack<T> listen(RTMEvent e, Handler<T> handler);
    Slack<T> schedule(String cron, Task handler);
}
