package org.eno.bot.api;

public abstract class EnoBotMessageBase {

    private String enoBotMessageType = "";

    protected EnoBotMessageBase(final String enoBotMessageType) {
        this.enoBotMessageType = enoBotMessageType;
    }

    public String getEnoBotMessageType() {
        return enoBotMessageType;
    }
}
