package org.eno.bot.api;

public class Subscription extends EnoBotMessageBase {
    private String name;
    private int months;
    private String message;

    public Subscription() {
        super("Subscription");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "name='" + name + '\'' +
                ", months=" + months +
                ", message='" + message + '\'' +
                '}';
    }
}
