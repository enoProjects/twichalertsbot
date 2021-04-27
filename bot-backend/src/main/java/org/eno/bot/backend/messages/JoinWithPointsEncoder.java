package org.eno.bot.backend.messages;

import com.google.gson.Gson;
import org.eno.bot.api.JoinWithPoints;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class JoinWithPointsEncoder implements Encoder.Text<JoinWithPoints> {

    private static Gson gson = new Gson();

    @Override
    public String encode(JoinWithPoints chatMessage) throws EncodeException {
        return gson.toJson(chatMessage);
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
