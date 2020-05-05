package com.defa.slack.rtm.event;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonEvent implements Event<JsonNode> {
    private final JsonNode root;

    public JsonEvent(JsonNode node){
        this.root = node;
    }

    @Override
    public JsonNode getEvent() {
        return this.root;
    }
}
