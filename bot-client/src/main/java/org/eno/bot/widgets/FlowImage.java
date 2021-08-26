package org.eno.bot.widgets;

import elemental2.dom.*;
import org.eno.bot.widgets.effects.RepeatOnDelayEffect;

public class FlowImage
        extends EnoItem {

    private int x = -500;

    private Image image = new Image();
    private final HTMLDivElement container;

    public FlowImage(int x, int y) {

        image.alt = "enoPog";
        image.src = "images/eno_pog_265.png";

        container = (HTMLDivElement) DomGlobal.document.createElement("div");
        container.style.position = "relative";
        container.style.zIndex = CSSProperties.ZIndexUnionType.of("-1");
        container.style.left = x + "px";
        container.append(image);

    }

    @Override
    public HTMLElement getElement() {
        return container;
    }

}
