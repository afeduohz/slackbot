package com.defa.slack.message.block.element;

import com.defa.slack.message.composition.PlainTextObject;
import com.defa.slack.message.composition.ConfirmationDialogObject;
import com.defa.slack.message.composition.OptionObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class StaticOptionsSelectElement extends AbstractActionElement {

    private PlainTextObject placeholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("initial_option")
    private OptionObject initialOption;

    private List<OptionObject> options;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationDialogObject confirm;

    public StaticOptionsSelectElement(){
        super(Type.STATIC_SELECT);
        this.placeholder = new PlainTextObject();
        this.options = new ArrayList<>();
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

    public List<OptionObject> getOptions() {
        return options;
    }

    public ConfirmationDialogObject getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmationDialogObject confirm) {
        this.confirm = confirm;
    }
}
