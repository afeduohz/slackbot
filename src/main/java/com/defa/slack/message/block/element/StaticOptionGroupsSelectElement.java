package com.defa.slack.message.block.element;

import com.defa.slack.message.composition.ConfirmationDialogObject;
import com.defa.slack.message.composition.OptionGroupObject;
import com.defa.slack.message.composition.OptionObject;
import com.defa.slack.message.composition.PlainTextObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class StaticOptionGroupsSelectElement extends AbstractActionElement {

    private PlainTextObject placeholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("initial_option")
    private OptionObject initialOption;

    @JsonProperty("option_groups")
    private List<OptionGroupObject> optionGroups;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationDialogObject confirm;

    public StaticOptionGroupsSelectElement(){
        super(Type.STATIC_SELECT);
        this.placeholder = new PlainTextObject();
        this.optionGroups = new ArrayList<>();
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

    public List<OptionGroupObject> getOptionGroups() {
        return optionGroups;
    }

    public ConfirmationDialogObject getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmationDialogObject confirm) {
        this.confirm = confirm;
    }
}
