package com.defa.slack;

import com.defa.slack.api.Methods;
import com.defa.slack.rtm.Connector;
import com.defa.slack.rtm.Resolver;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public final class Slack implements Connector {
    private WebSocketClient client;
    private final Resolver resolver;
    private final Methods methods;

    public Slack(String token, Resolver resolver) {
        assert token != null && resolver != null;
        this.methods = Methods.instance(token);
        this.resolver = resolver;
    }

    @Override
    public boolean connected() {
        return this.client != null && this.client.isOpen();
    }

    @Override
    public void connect() {
        if (!this.connected()) {
            methods.rtmConnect(null, response -> {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response);
                JsonNode okay = root.get("ok");
                if (okay != null) {
                    boolean ok = okay.asBoolean();
                    if (ok) {
                        JsonNode url = root.get("url");
                        if (url != null) {
                            boot(url.asText());
                        }
                    } else {
                        System.out.println(response);
                    }
                }
            });
        }

    }

    @Override
    public void disconnect() {
        if(this.connected()){
            this.client.close();
        }
    }

    private void resolve(final String message) {
        this.resolver.resolve(this, this.methods, message);
    }

    private void cron() {
        this.resolver.cron(this, this.methods);
    }

    private void boot(String uri) {
        try {
            client = new WebSocketClient(new URI(uri), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("[slack connected]");
                    cron();
                }

                @Override
                public void onMessage(String s) {
                    resolve(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("[slack disconnected]");
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            };

            client.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
