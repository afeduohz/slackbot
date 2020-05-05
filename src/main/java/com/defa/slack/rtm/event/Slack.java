package com.defa.slack.rtm.event;

import com.defa.slack.job.Task;

public interface Slack<T> {
    SlackResolver<T> listen(RTMEvent e, Handler<T> handler);
    SlackResolver<T> schedule(String cron, Task handler);
}
