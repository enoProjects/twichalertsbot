package org.eno.bot.api;

public class ChatMessage extends EnoBotMessageBase {
    private String from;
    private String content;

    public ChatMessage() {
        super("ChatMessage");
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
        return "ChatMessage{" +
                "from='" + from + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
