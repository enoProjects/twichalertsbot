package org.eno.bot.backend.messages;

import com.google.gson.Gson;
import org.eno.bot.api.FooterChange;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class FooterChangeEncoder implements Encoder.Text<FooterChange> {

    private static Gson gson = new Gson();

    @Override
    public String encode(FooterChange footerChange) throws EncodeException {
        return gson.toJson(footerChange);
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
