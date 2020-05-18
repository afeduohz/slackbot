package com.defa.slack.message.block.element;

import com.defa.slack.message.composition.ConfirmationDialogObject;
import com.defa.slack.message.composition.PlainTextObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelsSelectElement extends AbstractActionElement {
    private PlainTextObject placeholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("initial_channel")
    private String initialChannel;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationDialogObject confirm;

    public ChannelsSelectElement(){
        super(Type.CHANNELS_SELECT);
        this.placeholder = new PlainTextObject();
    }

    public PlainTextObject getPlaceholder() {
        return placeholder;
    }

    public String getInitialChannel() {
        return initialChannel;
    }

    public void setInitialChannel(String initialChannel) {
        this.initialChannel = initialChannel;
    }

    public ConfirmationDialogObject getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmationDialogObject confirm) {
        this.confirm = confirm;
    }
}
