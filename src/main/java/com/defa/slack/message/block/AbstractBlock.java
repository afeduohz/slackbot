package com.defa.slack.message.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractBlock {

    public enum Type {
        DIVIDER("divider"), IMAGE("image"), CONTEXT("context"), SECTION("section"), ACTIONS("actions");

        private final String type;

        Type(String type){
            this.type = type;
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }

    private final Type type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("block_id")
    private String blockId;

    public AbstractBlock(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }
}
