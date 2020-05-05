package com.defa.slack.message.composition;

import java.util.ArrayList;
import java.util.List;

public class OptionGroupObject implements CompositionObject {

    private PlainTextObject label;
    private List<OptionObject> options;

    public OptionGroupObject(){
        this.label = new PlainTextObject();
        this.options = new ArrayList<>();
    }

    public PlainTextObject getLabel() {
        return label;
    }

    public List<OptionObject> getOptions() {
        return options;
    }

    public void addOption(OptionObject option) {
        if(option!=null) this.options.add(option);
    }
}
