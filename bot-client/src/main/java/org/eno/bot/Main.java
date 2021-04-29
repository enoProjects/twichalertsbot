package org.eno.bot;

import elemental2.dom.CSSProperties;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.eno.bot.api.ChannelPointsRedeemed;
import org.eno.bot.api.Follow;
import org.eno.bot.api.Subscription;
import org.eno.bot.util.EventBus;
import org.eno.bot.widgets.*;
import org.eno.bot.widgets.effects.SoundEffect;
import org.eno.bot.widgets.effects.TimeoutEffect;

import java.util.LinkedList;
import java.util.Queue;

public class Main implements EnoElement {

    private HTMLDivElement element = (HTMLDivElement) DomGlobal.document.createElement("div");
    private HTMLDivElement main = (HTMLDivElement) DomGlobal.document.createElement("div");
    private HTMLDivElement support = (HTMLDivElement) DomGlobal.document.createElement("div");

    private FlowLane joinFlowLaneTop = new FlowLane(FlowLane.Direction.LEFT_TO_RIGHT);
    private FlowLane joinFlowLaneBottom = new FlowLane(FlowLane.Direction.RIGHT_TO_LEFT);
    private Footer footer = new Footer();

    private boolean leftRightToggle = true;

    private boolean canJoinToggle = false;

    private Queue<Subscription> subscriptionQueue = new LinkedList<>();
    private Queue<ChannelPointsRedeemed> channelPointsRedeemedQueue = new LinkedList<>();
    private Queue<Follow> followQueue = new LinkedList<>();

    public Main() {
        main.style.height = CSSProperties.HeightUnionType.of("200px");
        element.append(main);
        element.append(support);
        support.append(joinFlowLaneTop.getElement());
        support.append(joinFlowLaneBottom.getElement());

        footer.getBackgroundStyle().background = "#008080";

        support.append(footer.getElement());

        DomGlobal.setInterval(checkQueues(),
                500);

        EventBus.instance().listenSubscription(subscription -> {
            subscriptionQueue.add(subscription);
        });
        EventBus.instance().listenFollow(follow -> {
            followQueue.add(follow);
        });

        EventBus.instance().listenChannelPointsRedeemed(channelPointsRedeemed -> {
            channelPointsRedeemedQueue.add(channelPointsRedeemed);
        });

        EventBus.instance().listenJoinWithPoints(joinWithPoints -> {
            if (canJoinToggle) {
                if (leftRightToggle) {
                    joinFlowLaneTop.addItem(new FlowImage(100, 100));
                } else {
                    joinFlowLaneBottom.addItem(new FlowImage(100, 100));
                }
                leftRightToggle = !leftRightToggle;
            }
        });
    }

    private boolean isAlertRunning = false;

    private DomGlobal.SetIntervalCallbackFn checkQueues() {
        return p0 -> {
            if (!isAlertRunning && !subscriptionQueue.isEmpty()) {
                fireSubscription(subscriptionQueue.poll());
            } else if (!isAlertRunning && !channelPointsRedeemedQueue.isEmpty()) {
                fireChannelPointsRedeemed(channelPointsRedeemedQueue.poll());
            } else if (!isAlertRunning && !followQueue.isEmpty()) {
                fireFollow(followQueue.poll());
            } else if (!isAlertRunning) {
                clearJoins();
            }
        };
    }

    private void fireChannelPointsRedeemed(ChannelPointsRedeemed channelPointsRedeemed) {
        canJoinToggle = true;
        isAlertRunning = true;
        main.append(
                new EnoBossImage(channelPointsRedeemed.getUserInput(),
                        getTimeoutEffect()
//                        ,
//                        new SoundEffect("sounds/Jorgosjorisee.ogg")
                ).getElement());
    }

    private void fireFollow(Follow follow) {
        canJoinToggle = true;
        isAlertRunning = true;
        main.append(
                new EnoBossImage("Thank you for the follow " + follow.getName(),
                        getTimeoutEffect(),
                        new SoundEffect("sounds/Jorgosjorisee.ogg")
                ).getElement());
    }

    private void fireSubscription(Subscription subscription) {
        canJoinToggle = true;
        isAlertRunning = true;
        main.append(
                new EnoBossImage(subscription.getName() + " " + subscription.getMessage(),
                        getTimeoutEffect(),
                        new SoundEffect("sounds/Jorgosjorisee.ogg")
                ).getElement()
        );
    }

    private TimeoutEffect getTimeoutEffect() {
        return new TimeoutEffect() {
            @Override
            public DomGlobal.SetTimeoutCallbackFn getTimeoutCallback(HTMLElement component) {
                return p0 -> {
                    component.parentElement.removeChild(component);
                    isAlertRunning = false;
                };
            }

            @Override
            public double getTimeoutTime() {
                return 10000;
            }
        };
    }

    private void clearJoins() {
        joinFlowLaneTop.clear();
        joinFlowLaneBottom.clear();
        canJoinToggle = false;

    }

    @Override
    public HTMLElement getElement() {
        return element;
    }
}
