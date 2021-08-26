package org.eno.bot;

import elemental2.dom.DomGlobal;
import elemental2.dom.WebSocket;
import jsinterop.annotations.JsType;
import org.eno.bot.util.EventBus;

@JsType
public class App {

    public static void startApp() {
        DomGlobal.document.addEventListener("DOMContentLoaded", evt -> onModuleLoad());
    }

    public static void onModuleLoad() {

        Main main = new Main();

        DomGlobal.document.body.appendChild(main.getElement());

        WebSocket socket = new WebSocket("ws:localhost:8080/chat/eno");

        socket.onmessage = event -> {
            EventBus.instance().send(new BestJSONParserEver().parse(event.data.toString()));
        };
    }
}
