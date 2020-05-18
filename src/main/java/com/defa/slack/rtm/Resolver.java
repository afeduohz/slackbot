package com.defa.slack.rtm;

import com.defa.slack.api.Methods;

public interface Resolver {
    void resolve(final Connector connector, final Methods methods, final String message);
    void cron(final Connector connector, final Methods methods);
}
