package org.eno.bot.widgets;

import elemental2.dom.*;
import org.eno.bot.widgets.effects.RepeatOnDelayEffect;

public class FlowLane extends EnoItem {

    private final Direction direction;
    private final boolean flip;
    private final int movementStep;
    private final int delay;

    public enum Direction {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    private final HTMLDivElement container;

    private final int START_X = -DomGlobal.window.innerWidth;
    private final int END_X = DomGlobal.window.innerWidth;
    private int x = 100;

    public FlowLane(final Direction direction) {
        this(direction,
                direction.equals(Direction.RIGHT_TO_LEFT),
                10,
                30);
    }

    public FlowLane(final Direction direction,
                    final boolean flip,
                    final int movementStep,
                    final int delay) {
        this.direction = direction;
        this.flip = flip;
        this.movementStep = movementStep;
        this.delay = delay;

        container = (HTMLDivElement) DomGlobal.document.createElement("div");
        container.style.position = "relative";
        container.style.zIndex = CSSProperties.ZIndexUnionType.of("1");
        container.style.left = x + "px";

        container.style.overflow = "hidden";

        addEffect(new RepeatOnDelayEffect() {
            @Override
            public DomGlobal.SetIntervalCallbackFn getEffectCallback(HTMLElement component) {
                return createIntervalCallback(component);
            }

            @Override
            public double getDelay() {
                return delay;
            }
        });
    }

    public void addItem(EnoItem item) {
        final HTMLElement element = item.getElement();
        element.style.cssFloat = "left";
        if (flip) {
            element.style.transform = "scaleX(-1)";
        }
        container.append(element);
    }

    public void clear() {
        NodeList<Node> childNodes = container.childNodes;
        childNodes.forEach((currentValue, currentIndex, listObj) -> container.removeChild(currentValue));
    }

    @Override
    public HTMLElement getElement() {
        return container;
    }

    private DomGlobal.SetIntervalCallbackFn createIntervalCallback(final HTMLElement component) {
        return p0 -> {
            switch (direction) {

                case LEFT_TO_RIGHT:
                    x += movementStep;
                    component.style.left = x + "px";
                    if (x > END_X) {
                        x = START_X;
                    }
                    break;
                case RIGHT_TO_LEFT:
                    x -= movementStep;
                    component.style.left = x + "px";
                    if (x < START_X) {
                        x = END_X;
                    }
                    break;
            }
        };
    }

}
