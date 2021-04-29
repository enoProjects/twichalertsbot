package org.eno.bot;

import elemental2.dom.DomGlobal;
import elemental2.dom.WebSocket;
import org.eno.bot.util.EventBus;

public class App {

    private Main main = new Main();

    public void onModuleLoad() {

        DomGlobal.document.body.appendChild(main.getElement());

        WebSocket socket = new WebSocket("ws:localhost:8080/chat/eno");

        socket.onmessage = event -> {
            EventBus.instance().send(new BestJSONParserEver().parse(event.data.toString()));
        };
    }
}
