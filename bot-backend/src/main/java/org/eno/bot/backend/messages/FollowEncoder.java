package org.eno.bot.backend.messages;

import com.google.gson.Gson;
import org.eno.bot.api.Follow;
import org.eno.bot.api.Subscription;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class FollowEncoder implements Encoder.Text<Follow> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Follow message) throws EncodeException {
        return gson.toJson(message);
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
