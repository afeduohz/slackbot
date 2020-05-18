package com.defa.slack.message.block.element;

import com.defa.slack.message.composition.ConfirmationDialogObject;
import com.defa.slack.message.composition.PlainTextObject;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ButtonElement extends AbstractActionElement {

    private PlainTextObject text;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationDialogObject confirm;

    public ButtonElement(){
        super(Type.BUTTON);
        this.text = new PlainTextObject();
    }

    public PlainTextObject getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ConfirmationDialogObject getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmationDialogObject confirm) {
        this.confirm = confirm;
    }
}
