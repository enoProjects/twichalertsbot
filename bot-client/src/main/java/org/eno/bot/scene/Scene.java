package org.eno.bot.scene;

import org.treblereel.gwt.xml.mapper.api.annotation.XMLMapper;

import java.util.ArrayList;
import java.util.List;

@XMLMapper
public class Scene
        extends Element {

    private final String version = "0.1";
    private String name;
    private List<Element> elements = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public List<Element> getElements() {
        return elements;
    }
}
