package com.defa.slack.message.block.element;

import com.defa.slack.message.block.ContextObject;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageElement extends AbstractElement implements ContextObject {
    @JsonProperty("image_url")
    private String imageUrl = "";
    @JsonProperty("alt_text")
    private String altText = "";

    public ImageElement(){
        super(Type.IMAGE);
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
}
