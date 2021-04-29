package org.eno.bot.backend.messages;

import com.google.gson.Gson;
import org.eno.bot.api.ChannelPointsRedeemed;
import org.eno.bot.api.Follow;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChannelPointsRedeemedEncoder implements Encoder.Text<ChannelPointsRedeemed> {

    private static Gson gson = new Gson();

    @Override
    public String encode(ChannelPointsRedeemed message) throws EncodeException {
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
