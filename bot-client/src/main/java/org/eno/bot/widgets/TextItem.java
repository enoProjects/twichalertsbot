package org.eno.bot.widgets;

import elemental2.dom.*;

public class TextItem extends EnoItem {

    private final HTMLDivElement containerContainer;

    public TextItem(final String text) {
        containerContainer = (HTMLDivElement) DomGlobal.document.createElement("div");
        final Text textNode = DomGlobal.document.createTextNode(text);
        containerContainer.append(textNode);
    }

    public CSSStyleDeclaration getStyle() {
        return containerContainer.style;
    }

    @Override
    public HTMLElement getElement() {
        return containerContainer;
    }
}
