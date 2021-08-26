package org.eno.bot.scene;

import org.treblereel.gwt.xml.mapper.api.annotation.XMLMapper;

@XMLMapper
public class Style {

    private String backgroundColor;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
