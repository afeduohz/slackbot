package com.defa.slack.message.builder;

import com.defa.slack.message.UnsupportedPropertyException;
import com.defa.slack.message.block.element.ImageElement;

public final class ImageElementBuilder {
    private String url;
    private String alt;

    private ImageElementBuilder() {}

    public static ImageElementBuilder builder() {
        return new ImageElementBuilder();
    }

    public ImageElementBuilder url(final String url) {
        this.url = url;
        return this;
    }

    public ImageElementBuilder alt(final String alt) {
        this.alt = alt;
        return this;
    }

    public ImageElement build() {
        if(alt == null || alt.length() == 0) throw new UnsupportedPropertyException("alt is not proper.");
        if(url == null || url.length() == 0) throw new UnsupportedPropertyException("url is not proper.");
        ImageElement image = new ImageElement();
        image.setImageUrl(url);
        image.setAltText(alt);
        return image;
    }


}
