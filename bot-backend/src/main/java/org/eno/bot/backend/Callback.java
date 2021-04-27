package org.eno.bot.backend;

public interface Callback<T> {

    void onEvent( T t);
}
