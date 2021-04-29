package org.eno.bot.api;

public class ChannelPointsRedeemed extends EnoBotMessageBase {
    public static final String ID = "ChannelPointsRedeemed";

    private String redeemerDisplayName;
    private String title;
    private String userInput;


    public ChannelPointsRedeemed() {
        super(ID);
    }

    public String getRedeemerDisplayName() {
        return redeemerDisplayName;
    }

    public void setRedeemerDisplayName(String redeemerDisplayName) {
        this.redeemerDisplayName = redeemerDisplayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
