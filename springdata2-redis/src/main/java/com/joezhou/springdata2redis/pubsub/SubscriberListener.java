package com.joezhou.springdata2redis.pubsub;

import redis.clients.jedis.JedisPubSub;

/**
 * @author JoeZhou
 */
public class SubscriberListener extends JedisPubSub {

    private String subscriberName;

    public SubscriberListener(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.printf("onMessage(): %s get message '%s' from channel %s\n", subscriberName, message, channel);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.printf("onSubscribe(): %s subscribe channel %s-%d\n", subscriberName, channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.printf("onUnsubscribe(): %s unsubscribe channel %s-%d\n", subscriberName, channel, subscribedChannels);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.printf("onPSubscribe(): %s psubscribe channel %s-%d\n", subscriberName, pattern, subscribedChannels);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.printf("onPMessage(): %s get message '%s' from channel %s\n", subscriberName, message, channel);
    }
}
