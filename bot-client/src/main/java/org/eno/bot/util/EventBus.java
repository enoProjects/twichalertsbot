package org.eno.bot.util;

import org.eno.bot.api.Follow;
import org.eno.bot.api.JoinWithPoints;
import org.eno.bot.api.Subscription;

import java.util.ArrayList;
import java.util.Collection;

public class EventBus {

    private static final EventBus INSTANCE = new EventBus();

    private Collection<EventBusListener<Subscription>> subscriptionListeners = new ArrayList<>();
    private Collection<EventBusListener<Follow>> followListeners = new ArrayList<>();
    private Collection<EventBusListener<JoinWithPoints>> joinWithPointsListeners = new ArrayList<>();

    private EventBus() {
    }

    public static EventBus instance() {
        return INSTANCE;
    }

    public void listenSubscription(final EventBusListener<Subscription> listener) {
        subscriptionListeners.add(listener);
    }

    public void listenFollow(final EventBusListener<Follow> listener) {
        followListeners.add(listener);
    }

    public void listenJoinWithPoints(final EventBusListener<JoinWithPoints> listener) {
        joinWithPointsListeners.add(listener);
    }

    public void send(final Object o) {

        if (o instanceof Subscription) {
            for (EventBusListener<Subscription> subscriptionListener : subscriptionListeners) {
                subscriptionListener.onEvent((Subscription) o);
            }
        } else if (o instanceof Follow) {
            for (EventBusListener<Follow> followListener : followListeners) {
                followListener.onEvent((Follow) o);
            }

        } else if (o instanceof JoinWithPoints) {
            for (EventBusListener<JoinWithPoints> joinWithPointsListener : joinWithPointsListeners) {
                joinWithPointsListener.onEvent((JoinWithPoints) o);
            }

        }
    }
}


