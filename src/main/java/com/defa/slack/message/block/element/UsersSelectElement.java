package com.defa.slack.message.block.element;

import com.defa.slack.message.composition.ConfirmationDialogObject;
import com.defa.slack.message.composition.PlainTextObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsersSelectElement extends AbstractActionElement {
    private PlainTextObject placeholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("initial_user")
    private String initialUser;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationDialogObject confirm;

    public UsersSelectElement(){
        super(Type.USERS_SELECT);
        this.placeholder = new PlainTextObject();
    }

    public PlainTextObject getPlaceholder() {
        return placeholder;
    }

    public String getInitialUser() {
        return initialUser;
    }

    public void setInitialUser(String initialUser) {
        this.initialUser = initialUser;
    }

    public ConfirmationDialogObject getConfirm() {
        return confirm;
    }

    public void setConfirm(ConfirmationDialogObject confirm) {
        this.confirm = confirm;
    }
}
