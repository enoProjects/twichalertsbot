package org.eno.bot.util;

public interface EventBusListener<T> {
    void onEvent(T o);
}
