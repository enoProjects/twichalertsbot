package org.eno.bot.scene;

import org.treblereel.gwt.xml.mapper.api.annotation.XMLMapper;


@XMLMapper
public class Element {

    protected Transform transform;

    protected Style style;

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(final Transform transform) {
        this.transform = transform;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
