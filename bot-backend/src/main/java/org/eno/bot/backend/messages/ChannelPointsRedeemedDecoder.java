package org.eno.bot.backend.messages;

import com.google.gson.Gson;
import org.eno.bot.api.ChannelPointsRedeemed;
import org.eno.bot.api.Follow;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChannelPointsRedeemedDecoder implements Decoder.Text<ChannelPointsRedeemed> {

    private static Gson gson = new Gson();

    @Override
    public ChannelPointsRedeemed decode(String s) throws DecodeException {
        return gson.fromJson(s, ChannelPointsRedeemed.class);
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
