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
import com.github.twitch4j.chat.events.channel.SubscriptionEvent;
import com.github.twitch4j.common.events.user.PrivateMessageEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import org.eno.credentials.Credentials;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TwitchConnection {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TwitchConnection.class);

    public final static String ENODEV_CHANNEL = "eno_dev";

    private OAuth2Credential oAuth2CredentialHere = new OAuth2Credential("twitch", Credentials.getInstance().getMainAuth());
    private OAuth2Credential botOAuth2CredentialHere = new OAuth2Credential("twitch", Credentials.getInstance().getBotAuth());
    private TwitchClient twitchClient;

    @Inject
    public TwitchConnection() {
    }

    private boolean hasBeenInitialized = false;

    public TwitchClient getTwitchClient() {
        return twitchClient;
    }

    private void makeBotChatConnection(final Callback<ChannelMessageEvent> channelMessageEventCallback,
                                       final Callback<SubscriptionEvent> subscriptionEventCallback,
                                       final Callback<FollowEvent> followEventCallback) {
        final CredentialManager credentialManager = CredentialManagerBuilder.builder().build();
        credentialManager.registerIdentityProvider(
                new TwitchIdentityProvider(
                        Credentials.getInstance().getClientId(),
                        Credentials.getInstance().getBotAuth(),
                        ""));

        final TwitchChat chatClient = TwitchChatBuilder.builder()
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
                    log.info("Got a follow from " + followEvent.getUser());
                    followEventCallback.onEvent(followEvent);
                });
        eventHandler.onEvent(SubscriptionEvent.class,
                subscriptionEvent -> {
                    log.info("Got a follow from " + subscriptionEvent.getUser());
                    subscriptionEventCallback.onEvent(subscriptionEvent);
                });

        chatClient.joinChannel(ENODEV_CHANNEL);
        chatClient.sendMessage(ENODEV_CHANNEL, "hello");
    }

    public void init(final Callback<ChannelMessageEvent> channelMessageEventCallback,
                     final Callback<SubscriptionEvent> subscriptionEventCallback,
                     final Callback<FollowEvent> followEventCallback,
                     final Callback<RewardRedeemedEvent> rewardRedeemedEventCallback) {
        if (hasBeenInitialized) {
            return;
        }
        hasBeenInitialized = true;

        final CredentialManager credentialManager = CredentialManagerBuilder.builder().build();
        credentialManager.registerIdentityProvider(new TwitchIdentityProvider(Credentials.getInstance().getClientId(), Credentials.getInstance().getMainAuth(), ""));

        twitchClient = TwitchClientBuilder.builder()
                .withEnableTMI(true)
                .withClientId(Credentials.getInstance().getClientId())
                .withClientSecret(Credentials.getInstance().getClientSecret())
                .withEnablePubSub(true)
                .build();

        makeBotChatConnection(
                channelMessageEventCallback,
                subscriptionEventCallback,
                followEventCallback);

        twitchClient.getPubSub().listenForFollowingEvents(oAuth2CredentialHere, Credentials.getInstance().getChannelId());
        twitchClient.getPubSub().listenForWhisperEvents(oAuth2CredentialHere, Credentials.getInstance().getChannelId());
        twitchClient.getPubSub().listenForChannelPointsRedemptionEvents(oAuth2CredentialHere, Credentials.getInstance().getChannelId());

        twitchClient.getEventManager().onEvent(RewardRedeemedEvent.class,
                x -> rewardRedeemedEventCallback.onEvent(x)
        );
    }

}
