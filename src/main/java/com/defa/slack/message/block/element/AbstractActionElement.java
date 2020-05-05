package com.defa.slack.message.block.element;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractActionElement extends AbstractElement {
    @JsonProperty("action_id")
    private String actionId = "";

    public AbstractActionElement(Type type){
        super(type);
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

}
