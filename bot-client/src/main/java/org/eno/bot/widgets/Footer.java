package org.eno.bot.widgets;

import elemental2.dom.*;
import org.eno.bot.util.EventBus;

public class Footer extends EnoItem {

    private final HTMLDivElement container;

    private FlowLane textFlowLaneFooter = new FlowLane(
            FlowLane.Direction.RIGHT_TO_LEFT,
            false,
            5,
            30);

    public Footer() {

        // TODO allow channel moderators to update footer text
        // TODO allow usage of special characters like < > in messages in the clients
        // TODO use emotes in messages
        // TODO make a better follow sound
        // TODO Change alert text so that it is visible (Currently black is hard to see)

        container = (HTMLDivElement) DomGlobal.document.createElement("div");

        container.style.position = "fixed";
        container.style.bottom = "0";
        container.style.height = CSSProperties.HeightUnionType.of("35px");
        container.style.width = CSSProperties.WidthUnionType.of("100%");

        addItem(textFlowLaneFooter);

        EventBus.instance().listenFooterChange(footerChange -> {

            textFlowLaneFooter.clear();

            final TextItem textItem = new TextItem(footerChange.getContent());
            textItem.getStyle().fontSize = CSSProperties.FontSizeUnionType.of("20px");
            textItem.getStyle().color = "white";
            textFlowLaneFooter.addItem(textItem);
        });
    }

    public CSSStyleDeclaration getBackgroundStyle() {
        return container.style; // TODO dirty
    }

    @Override
    public HTMLElement getElement() {
        return container;
    }

    public void addItem(EnoItem enoItem) {
        container.append(enoItem.getElement());
    }

}
