package org.eno.bot;

import org.eno.bot.api.ChatMessage;
import org.eno.bot.api.Follow;
import org.eno.bot.api.JoinWithPoints;
import org.eno.bot.api.Subscription;

public class BestJSONParserEver {

    public String parseType(final String json) {
        return new Parser(json).getString("enoBotMessageType");
    }

    public Object parse(String json) {
        Parser parser = new Parser(json);


        switch (parser.getString("enoBotMessageType")) {
            case "Subscription":

                return parser.parseSubscription();
            case "JoinWithPoints":

                return parser.parseJoinWithPoints();
            case "ChatMessage":

                return parser.parseChatMessage();
            case "Follow":

                return parser.parseFollow();

        }

        return null;
    }

    class Parser {
        private String json;

        public Parser(final String json) {
            this.json = json;
        }

        public String getString(final String key) {
            final String fullKey = "\"" + key + "\":";
            String substring = json.substring(json.indexOf(fullKey) + 1 + fullKey.length());
            return substring.substring(0, substring.indexOf("\""));
        }

        public int getInt(final String key) {
            final String fullKey = "\"" + key + "\":";
            String substring = json.substring(json.indexOf(fullKey) + fullKey.length());
            return Integer.parseInt(substring.substring(0, substring.indexOf(",")));
        }

        public Subscription parseSubscription() {
            final Subscription subscription = new Subscription();
            subscription.setMessage(getString("message"));
            subscription.setName(getString("name"));
            subscription.setMonths(getInt("months"));
            return subscription;
        }

        public ChatMessage parseChatMessage() {
            final ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent(getString("content"));
            chatMessage.setFrom(getString("from"));
            return chatMessage;
        }

        public Object parseJoinWithPoints() {
            final JoinWithPoints joinWithPoints = new JoinWithPoints();
            joinWithPoints.setContent(getString("content"));
            joinWithPoints.setFrom(getString("from"));
            return joinWithPoints;
        }

        public Object parseFollow() {
            final Follow follow = new Follow();
            follow.setName(getString("name"));
            return follow;
        }
    }
}
