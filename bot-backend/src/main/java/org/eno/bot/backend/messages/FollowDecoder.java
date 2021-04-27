package org.eno.bot.backend.messages;

import com.google.gson.Gson;
import org.eno.bot.api.Follow;
import org.eno.bot.api.Subscription;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class FollowDecoder implements Decoder.Text<Follow> {

    private static Gson gson = new Gson();

    @Override
    public Follow decode(String s) throws DecodeException {
        return gson.fromJson(s, Follow.class);
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
