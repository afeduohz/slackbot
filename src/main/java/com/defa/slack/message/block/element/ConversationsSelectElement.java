package com.defa.slack.message.block.element;

import com.defa.slack.message.composition.PlainTextObject;
import com.defa.slack.message.composition.ConfirmationDialogObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversationsSelectElement extends AbstractActionElement {
    private PlainTextObject placeholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("initial_conversation")
    private String initialConversation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationDialogObject confirm;

    public ConversationsSelectElement(){
        super(Type.CONVERSATIONS_SELECT);
        this.placeholder = new PlainTextObject();
    }

    public PlainTextObject getPlaceholder() {
        return placeholder;
    }

    public String getInitialConversation() {
        return initialConversation;
    }

    public void setInitialConversation(String initialConversation) {
        this.initialConversation = initialConversation;
    }

    public ConfirmationDialogObject getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmationDialogObject confirm) {
        this.confirm = confirm;
    }
}
