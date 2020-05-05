package com.defa.slack.message.block;

import java.util.ArrayList;
import java.util.List;

public class Context extends AbstractBlock {

    private List<ContextObject> elements;

    public Context(){
        super(Type.CONTEXT);
        this.elements = new ArrayList<>();
    }

    public List<ContextObject> getElements() {
        return elements;
    }

    public void setElements(List<ContextObject> elements) {
        if(elements!=null && elements.size()<=10) {
            this.elements = elements;
        }
    }

    public void addElement(ContextObject element) {
        if(element !=null && elements.size()<10) this.elements.add(element);
    }

}
