package org.eno.bot.backend.messages;

import com.google.gson.Gson;
import org.eno.bot.api.FooterChange;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class FooterChangeDecoder implements Decoder.Text<FooterChange> {

    private static Gson gson = new Gson();

    @Override
    public FooterChange decode(String s) throws DecodeException {
        return gson.fromJson(s, FooterChange.class);
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
