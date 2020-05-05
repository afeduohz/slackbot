package com.defa.slack.message.block;

import com.defa.slack.message.composition.PlainTextObject;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Image extends AbstractBlock {

    @JsonProperty("image_url")
    private String imageUrl = "";
    @JsonProperty("alt_text")
    private String altText = "";

    private PlainTextObject title;

    public Image(){
        super(Type.IMAGE);
        this.title = new PlainTextObject();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public PlainTextObject getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
