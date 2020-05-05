package com.defa.slack.message.block.element;

import com.defa.slack.message.composition.PlainTextObject;
import com.defa.slack.message.composition.ConfirmationDialogObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DatePickerElement extends AbstractActionElement {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PlainTextObject placeholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("initial_date")
    private String initialDate = "";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationDialogObject confirm;

    public DatePickerElement(){
        super(Type.DATEPICKER);
    }

    public PlainTextObject getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(PlainTextObject placeholder) {
        this.placeholder = placeholder;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public ConfirmationDialogObject getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmationDialogObject confirm) {
        this.confirm = confirm;
    }
}
