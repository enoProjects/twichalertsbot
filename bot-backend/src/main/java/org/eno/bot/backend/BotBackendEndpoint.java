package org.eno.bot.backend;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chat.events.channel.FollowEvent;
import org.eno.bot.api.ChatMessage;
import org.eno.bot.api.Follow;
import org.eno.bot.api.JoinWithPoints;
import org.eno.bot.api.Subscription;
import org.eno.bot.backend.messages.*;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat/{username}",
        decoders = {
                MessageDecoder.class,
                SubscriptionDecoder.class,
                JoinWithPointsDecoder.class,
                FollowDecoder.class},
        encoders = {
                MessageEncoder.class,
                SubscriptionEncoder.class,
                JoinWithPointsEncoder.class,
                FollowEncoder.class})
public class BotBackendEndpoint {

    @Inject
    private TwitchConnection twitchConnection;

    private Session session;
    private static Set<BotBackendEndpoint> botBackendEndpoints
            = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username) throws IOException, EncodeException {

        this.session = session;
        botBackendEndpoints.add(this);
        users.put(session.getId(), username);

        twitchConnection.init(
                getChannelMessageEventCallback(),
                getFollowEventCallback());
    }

    private Callback<FollowEvent> getFollowEventCallback() {
        return followEvent -> {
            final Follow follow = new Follow();
            follow.setName(followEvent.getUser().getName());

            safeBroadCast(follow);
        };
    }

    @NotNull
    private Callback<ChannelMessageEvent> getChannelMessageEventCallback() {
        return channelMessageEvent -> {
            if (channelMessageEvent.getMessage().startsWith("!sub")) {

                final Subscription sub = new Subscription();
                sub.setName("Kalle");
                sub.setMonths(4);
                sub.setMessage("Thanks for content");

                safeBroadCast(sub);

            } else if (channelMessageEvent.getMessage().startsWith("!follow")) {

                final Follow follow = new Follow();
                follow.setName(channelMessageEvent.getUser().getName());

                safeBroadCast(follow);
            } else if (channelMessageEvent.getMessage().startsWith("!join")) {

                final JoinWithPoints joinWithPoints = new JoinWithPoints();
                joinWithPoints.setFrom(channelMessageEvent.getUser().getName());
                joinWithPoints.setContent(channelMessageEvent.getMessage());

                safeBroadCast(joinWithPoints);
            } else {

                final ChatMessage chatMessage = new ChatMessage();
                chatMessage.setFrom(channelMessageEvent.getUser().getName());
                chatMessage.setContent(channelMessageEvent.getMessage());

                safeBroadCast(chatMessage);
            }
        };
    }

    private void safeBroadCast(Object sub) {
        try {
            broadcast(sub);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(Session session, ChatMessage chatMessage)
            throws IOException, EncodeException {

        chatMessage.setFrom(users.get(session.getId()));
        broadcast(chatMessage);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {

        botBackendEndpoints.remove(this);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setFrom(users.get(session.getId()));
        chatMessage.setContent("Disconnected!");
        broadcast(chatMessage);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Object message)
            throws IOException, EncodeException {

        botBackendEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().
                            sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
