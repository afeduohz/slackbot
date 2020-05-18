package com.defa.slack.message.block.element;

import com.defa.slack.message.composition.ConfirmationDialogObject;
import com.defa.slack.message.composition.OptionObject;
import com.defa.slack.message.composition.PlainTextObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalsSelectElement extends AbstractActionElement {
    private PlainTextObject placeholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("initial_option")
    private OptionObject initialOption;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("min_query_length")
    private int minQueryLength;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationDialogObject confirm;

    public ExternalsSelectElement(){
        super(Type.EXTERNAL_SELECT);
        this.placeholder = new PlainTextObject();
    }

    public PlainTextObject getPlaceholder() {
        return placeholder;
    }

    public OptionObject getInitialOption() {
        return initialOption;
    }

    public void setInitialOption(OptionObject initialOption) {
        this.initialOption = initialOption;
    }

    public int getMinQueryLength() {
        return minQueryLength;
    }

    public void setMinQueryLength(int minQueryLength) {
        this.minQueryLength = minQueryLength;
    }

    public ConfirmationDialogObject getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmationDialogObject confirm) {
        this.confirm = confirm;
    }
}
