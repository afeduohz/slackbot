package com.defa.slack.message.composition;

public class ConfirmationDialogObject implements CompositionObject {
    private final PlainTextObject title;
    private AbstractTextObject text;
    private final PlainTextObject confirm;
    private final PlainTextObject deny;

    public ConfirmationDialogObject() {
        this.title = new PlainTextObject();
        this.text = new PlainTextObject();
        this.confirm = new PlainTextObject();
        this.deny = new PlainTextObject();
    }

    public PlainTextObject getTitle() {
        return title;
    }

    public AbstractTextObject getText() {
        return text;
    }

    public void setText(AbstractTextObject text) {
        this.text = text;
    }

    public PlainTextObject getConfirm() {
        return confirm;
    }

    public PlainTextObject getDeny() {
        return deny;
    }
}
