package org.eno.bot;

import elemental2.dom.*;
import org.eno.bot.util.EventBus;

public class App {

    private Main main = new Main();

    public void onModuleLoad() {

        DomGlobal.document.body.appendChild(main.getElement());

        final HTMLTextAreaElement textarea = (HTMLTextAreaElement) DomGlobal.document.createElement("textarea");
        textarea.style.height = CSSProperties.HeightUnionType.of("400px");
        textarea.style.width = CSSProperties.WidthUnionType.of("600px");

        HTMLDivElement footer = (HTMLDivElement) DomGlobal.document.createElement("div");
        footer.append(textarea);

        WebSocket socket = new WebSocket("ws:localhost:8080/chat/eno");


        socket.onmessage = event -> {

            textarea.textContent += event.data.toString();
            textarea.textContent += "\n";
            Object o = new BestJSONParserEver().parse(event.data.toString());
            textarea.textContent += o.toString();


            EventBus.instance().send(o);
        };
    }
}
