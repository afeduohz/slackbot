package com.defa.slack.message.composition;

public class PlainTextObject  extends AbstractTextObject {

    private boolean emoji;

    public PlainTextObject(){
        setType(null);
        setText(" "); //content cannot be empty. bug ?
    }

    public void setType(AbstractTextObject.Type type) {
        super.setType(Type.PLAIN_TEXT);
    }

    public boolean isEmoji() {
        return emoji;
    }

    public void setEmoji(boolean emoji) {
        this.emoji = emoji;
    }
}
