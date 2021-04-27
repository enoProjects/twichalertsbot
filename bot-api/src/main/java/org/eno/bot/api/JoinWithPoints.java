package org.eno.bot.api;

public class JoinWithPoints extends EnoBotMessageBase {
    private String from;
    private String content;

    public JoinWithPoints() {
        super("JoinWithPoints");
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JoinWithPoints{" +
                "from='" + from + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
