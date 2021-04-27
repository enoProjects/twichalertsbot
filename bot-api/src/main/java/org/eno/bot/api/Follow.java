package org.eno.bot.api;

public class Follow extends EnoBotMessageBase {
    private String name;

    public Follow() {
        super("Follow");
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "name='" + name + '\'' +
                '}';
    }
}
