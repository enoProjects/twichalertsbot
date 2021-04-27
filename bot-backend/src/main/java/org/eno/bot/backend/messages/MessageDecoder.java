package org.eno.bot.backend.messages;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;
import org.eno.bot.api.ChatMessage;

public class MessageDecoder implements Decoder.Text<ChatMessage> {

    private static Gson gson = new Gson();

    @Override
    public ChatMessage decode(String s) throws DecodeException {
        return gson.fromJson(s, ChatMessage.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
