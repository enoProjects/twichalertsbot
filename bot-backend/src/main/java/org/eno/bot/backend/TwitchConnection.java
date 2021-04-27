package org.eno.bot.backend;

import com.github.philippheuer.credentialmanager.CredentialManager;
import com.github.philippheuer.credentialmanager.CredentialManagerBuilder;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.auth.providers.TwitchIdentityProvider;
import com.github.twitch4j.chat.TwitchChat;
import com.github.twitch4j.chat.TwitchChatBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chat.events.channel.FollowEvent;
import com.github.twitch4j.common.events.user.PrivateMessageEvent;
import org.eno.Credentials;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TwitchConnection {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TwitchConnection.class);

    private OAuth2Credential oAuth2CredentialHere = new OAuth2Credential("twitch", Credentials.getAuth());
    private OAuth2Credential botOAuth2CredentialHere = new OAuth2Credential("twitch", Credentials.getBotAuth());
    private final static String ENODEV_CHANNEL = "eno_dev";

    @Inject
    public TwitchConnection() {
    }

    private boolean hasBeenInitialized = false;

    private void makeBotChatConnection(Callback<ChannelMessageEvent> channelMessageEventCallback, Callback<FollowEvent> followEventCallback) {
        final CredentialManager credentialManager = CredentialManagerBuilder.builder().build();
        credentialManager.registerIdentityProvider(
                new TwitchIdentityProvider(
                        Credentials.getClientId(),
                        Credentials.getBotAuth(),
                        ""));

        TwitchChat chatClient = TwitchChatBuilder.builder()
                .withCredentialManager(credentialManager)
                .withChatAccount(botOAuth2CredentialHere)
                .build();

        chatClient.getEventManager().onEvent(PrivateMessageEvent.class,
                privateMessageEvent -> log.info("Got pm " + privateMessageEvent.getUser()));
        final SimpleEventHandler eventHandler = chatClient.getEventManager().getEventHandler(SimpleEventHandler.class);

        eventHandler.onEvent(ChannelMessageEvent.class,
                channelMessageEvent -> {
                    log.info("Got channel message " + channelMessageEvent.getMessage());
                    channelMessageEventCallback.onEvent(channelMessageEvent);
                });
        eventHandler.onEvent(FollowEvent.class,
                followEvent -> {
                    log.info("Got follow " + followEvent.getUser());
                    followEventCallback.onEvent(followEvent);
                });

        chatClient.joinChannel(ENODEV_CHANNEL);
        chatClient.sendMessage(ENODEV_CHANNEL, "hello");
    }

    public void init(final Callback<ChannelMessageEvent> channelMessageEventCallback,
                     final Callback<FollowEvent> followEventCallback) {
        if (hasBeenInitialized) {
            return;
        }
        hasBeenInitialized = true;

        final CredentialManager credentialManager = CredentialManagerBuilder.builder().build();
        credentialManager.registerIdentityProvider(new TwitchIdentityProvider(Credentials.getClientId(), Credentials.getAuth(), ""));

        final TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withClientId(Credentials.getClientId())
                .withClientSecret(Credentials.getClientSecret())
//                .withEnableChat(true)
                .withEnablePubSub(true)
//                .withChatAccount(oAuth2CredentialHere)
                .build();

        makeBotChatConnection(
                channelMessageEventCallback,
                followEventCallback);

//        twitchClient.getPubSub().listenForFollowingEvents(oAuth2CredentialHere, Credentials.channelIdFromOauthToken());
//        twitchClient.getPubSub().listenForFollowingEvents(null, Credentials.channelIdFromOauthToken());
        twitchClient.getPubSub().listenForFollowingEvents(oAuth2CredentialHere, Credentials.channelId());
        twitchClient.getPubSub().listenForWhisperEvents(oAuth2CredentialHere, Credentials.channelId());

//        twitchClient.getEventManager().onEvent(PrivateMessageEvent.class,
//                privateMessageEvent -> log.info("Got pm " + privateMessageEvent.getUser()));

//        twitchClient.getChat().joinChannel(ENODEV_CHANNEL);
//        twitchClient.getChat().sendMessage(ENODEV_CHANNEL, "hello");

//        final SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);
//
//        eventHandler.onEvent(ChannelMessageEvent.class,
//                channelMessageEvent -> {
//                    log.info("Got channel message " + channelMessageEvent.getMessage());
//                    channelMessageEventCallback.onEvent(channelMessageEvent);
//                });
//        eventHandler.onEvent(FollowEvent.class,
//                followEvent -> {
//                    log.info("Got follow " + followEvent.getUser());
//                    followEventCallback.onEvent(followEvent);
//                });
    }

}
