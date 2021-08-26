package org.eno.bot.scene;

import org.treblereel.gwt.xml.mapper.api.annotation.XMLMapper;

@XMLMapper
public class Image
        extends Element {

    private String alt;
    private String url;


    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public String getUrl() {
        return url;
    }
}
