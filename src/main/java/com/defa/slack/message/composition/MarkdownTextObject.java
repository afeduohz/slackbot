package com.defa.slack.message.composition;

import com.defa.slack.message.Markdown;

public class MarkdownTextObject  extends AbstractTextObject {

    private boolean verbatim;

    public MarkdownTextObject(){
        setType(null);
        setText(" "); //content cannot be empty. bug ?
    }

    public void setType(Type type) {
        super.setType(Type.MARK_DOWN);
    }

    public boolean isVerbatim() {
        return verbatim;
    }

    public void setVerbatim(boolean verbatim) {
        this.verbatim = verbatim;
    }

    public void setText(Markdown markdown) {
        this.setText(markdown.format());
    }
}
