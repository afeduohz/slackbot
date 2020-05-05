package com.defa.slack.message;

import com.defa.slack.message.block.*;
import com.defa.slack.message.block.element.Element;
import com.defa.slack.message.composition.AbstractTextObject;
import com.defa.slack.message.composition.MarkdownTextObject;
import com.defa.slack.message.composition.PlainTextObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Blocks implements Formatter{
    private final List<AbstractBlock> blocks;

    private Blocks() {
        this.blocks = new ArrayList<>();
    }

    public static Blocks create() {
        return new Blocks();
    }

    public Blocks add(AbstractBlock block) {
        if(null != block) {
            this.blocks.add(block);
        }
        return this;
    }

    public Blocks divider() {
        return this.add(new Divider());
    }

    public Blocks section(final String plainText) {
        return section(plainText, null, null);
    }

    public Blocks section(final String plainText, final Element accessory) {
        return section(plainText, null, accessory);
    }

    public Blocks section(final String plainText, final List<AbstractTextObject> fields) {
        return section(plainText, fields, null);
    }

    public Blocks section(final String plainText, final List<AbstractTextObject> fields, final Element accessory) {
        if(plainText == null) throw new MissingPropertyException("Miss text.");
        Section section = new Section();
        PlainTextObject txt = new PlainTextObject();
        txt.setText(plainText);
        section.setText(txt);
        if(fields!=null) {
            section.setFields(fields);
        }
        if (accessory != null) {
            section.setAccessory(accessory);
        }
        return this.add(section);
    }

    public Blocks markdown(final String text) {
        return section(Markdown.create().plain(text));
    }

    public Blocks section(final Markdown markdown) {
        return section(markdown, null, null);
    }

    public Blocks section(final Markdown markdown, final Element accessory) {
        return section(markdown, null, accessory);
    }

    public Blocks section(final Markdown markdown, final List<AbstractTextObject> fields) {
        return section(markdown, fields, null);
    }

    public Blocks section(final Markdown markdown, final List<AbstractTextObject> fields, final Element accessory) {
        if(markdown == null) throw new MissingPropertyException("Miss text.");
        Section section = new Section();
        MarkdownTextObject txt = new MarkdownTextObject();
        txt.setText(markdown.format());
        section.setText(txt);
        if(fields!=null) {
            section.setFields(fields);
        }
        if (accessory != null) {
            section.setAccessory(accessory);
        }
        return this.add(section);
    }

    public Blocks image(final String url, final String alt) {
        Image image = new Image();
        image.setImageUrl(url);
        image.setAltText(alt);
        return this.add(image);
    }

    public Blocks image(final String url, final String alt, final String title) {
        Image image = new Image();
        image.setImageUrl(url);
        image.setAltText(alt);
        image.setTitle(title);
        return this.add(image);
    }

    public Blocks image(final String url, final String alt, final String title, final String blockId) {
        Image image = new Image();
        image.setImageUrl(url);
        image.setAltText(alt);
        image.setTitle(title);
        image.setBlockId(blockId);
        return this.add(image);
    }

    public Blocks context(final ContextObject... objects) {
        Context context = new Context();
        Arrays.stream(objects).forEach(context::addElement);
        return this.add(context);
    }

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this.blocks);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
