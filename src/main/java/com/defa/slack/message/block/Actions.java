package com.defa.slack.message.block;

import com.defa.slack.message.block.element.Element;

import java.util.ArrayList;
import java.util.List;

public class Actions extends AbstractBlock {

    private List<Element> elements;

    public Actions(){
        super(Type.ACTIONS);
        this.elements = new ArrayList<>();
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public void addElement(Element element){
        if(element!=null) this.elements.add(element);
    }
}
