package com.zhu.message.rocket.util;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * ClassName:    Test
 * Package:    com.jp.platform.saas.message.rocketmq.util
 * Description:
 * Datetime:    2025/3/13   15:17
 * Author:   朱殿辉
 */
public class Test {
    public static void main(String[] args) {
//        RocketMQProducerUtil producerUtil = null;
//        try {
//            producerUtil = new RocketMQProducerUtil("producer-group", "127.0.0.1:9876");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 发送同步消息
//        SendResult sendResult = null;
//        try {
//            sendResult = producerUtil.sendSyncMessage("test-topic", "test-tag", "Hello, RocketMQ!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("Sync Send Result: " + sendResult);
//
//        // 发送异步消息
//        try {
//            producerUtil.sendAsyncMessage("test-topic", "test-tag", "Hello, Async RocketMQ!", new SendCallback() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    System.out.println("Async Send Success: " + sendResult);
//                }
//
//                @Override
//                public void onException(Throwable e) {
//                    System.out.println("Async Send Exception: " + e.getMessage());
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 发送单向消息
//        try {
//            producerUtil.sendOnewayMessage("test-topic", "test-tag", "Hello, Oneway RocketMQ!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 发送延时消息
//        try {
//            producerUtil.sendDelayMessage("test-topic", "test-tag", "Hello, Delay RocketMQ!", 3);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 关闭生产者
//        producerUtil.shutdownProducer();

        Consumer consumerUtil = new Consumer("consumer-group", "127.0.0.1:9876");

        try {
            consumerUtil.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    System.out.println("Received Messages: " + msgs);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            }, "test-topic", "*");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            consumerUtil.startConsumer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 确保消费者持续运行
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭消费者
        consumerUtil.shutdownConsumer();
    }
}
