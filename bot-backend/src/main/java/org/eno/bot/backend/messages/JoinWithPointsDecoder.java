package org.eno.bot.backend.messages;

import com.google.gson.Gson;
import org.eno.bot.api.ChatMessage;
import org.eno.bot.api.JoinWithPoints;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class JoinWithPointsDecoder implements Decoder.Text<JoinWithPoints> {

    private static Gson gson = new Gson();

    @Override
    public JoinWithPoints decode(String s) throws DecodeException {
        return gson.fromJson(s, JoinWithPoints.class);
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
