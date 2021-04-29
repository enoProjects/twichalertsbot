package org.eno.bot.api;

public class FooterChange extends EnoBotMessageBase {
    public final static String FOOTER = "!footer ";
    public static final String ID = "FooterChange";

    private String from;
    private String content;

    public FooterChange() {
        super("FooterChange");
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
        return "FooterChange{" +
                "from='" + from + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
