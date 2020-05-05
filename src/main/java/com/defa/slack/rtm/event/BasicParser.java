package com.defa.slack.rtm.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BasicParser implements Parser<JsonNode> {
    @Override
    public TypeEvent<JsonNode> parse(String source) {
        ObjectMapper om = new ObjectMapper();
        try {
            JsonNode root = om.readTree(source);
            JsonNode type = root.findValue("type");
            if(null==type) return null;
            JsonNode subType = root.findValue("subtype");
            String eventName = type.asText();
            if(null!=subType) {
                eventName += "+" + subType.asText();
            }
            Event<JsonNode> event = new JsonEvent(root);
            return new TypeEvent<>(eventName, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
