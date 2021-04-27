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


//        DomGlobal.setInterval(createIntervalCallback(component), 30);

        container = (HTMLDivElement) DomGlobal.document.createElement("div");
        container.style.position = "relative";
        container.style.zIndex = CSSProperties.ZIndexUnionType.of("-1");
        container.style.left = x + "px";
        container.append(image);


//        addEffect(new RepeatOnDelayEffect() {
//            @Override
//            public DomGlobal.SetIntervalCallbackFn getEffectCallback(HTMLElement component) {
//                return createIntervalCallback(component);
//            }
//
//            @Override
//            public double getDelay() {
//                return 30;
//            }
//        });
    }

    @Override
    public HTMLElement getElement() {
        return container;
    }

    private DomGlobal.SetIntervalCallbackFn createIntervalCallback(final HTMLElement component) {
        return p0 -> {
            x += 10;
            component.style.left = x + "px";
            if (x > 2000) {
                x = -500;
            }
        };
    }
}
