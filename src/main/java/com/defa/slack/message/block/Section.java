package com.defa.slack.message.block;

import com.defa.slack.message.block.element.Element;
import com.defa.slack.message.composition.AbstractTextObject;
import com.defa.slack.message.composition.PlainTextObject;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Section extends AbstractBlock {

    private AbstractTextObject text;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AbstractTextObject> fields;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Element accessory;

    public Section(){
        super(Type.SECTION);
        setText(new PlainTextObject());
    }

    public AbstractTextObject getText() {
        return text;
    }

    public void setText(AbstractTextObject text) {
        this.text = text;
    }

    public List<AbstractTextObject> getFields() {
        return fields;
    }

    public void setFields(List<AbstractTextObject> fields) {
        this.fields = fields;
    }

    public Element getAccessory() {
        return accessory;
    }

    public void setAccessory(Element accessory) {
        this.accessory = accessory;
    }
}
