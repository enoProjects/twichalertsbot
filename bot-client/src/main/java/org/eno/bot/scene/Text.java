package org.eno.bot.scene;

import org.treblereel.gwt.xml.mapper.api.annotation.XMLMapper;

@XMLMapper
public class Text
        extends Element {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
