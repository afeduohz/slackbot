package com.defa.slack.rtm;

import com.defa.slack.api.Methods;

public interface Resolver {
    void resolve(final String message, final Methods methods);
    void cron(final Methods methods);
}
