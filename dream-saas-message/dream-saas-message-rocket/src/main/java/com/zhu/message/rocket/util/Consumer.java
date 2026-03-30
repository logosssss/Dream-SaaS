package com.zhu.message.rocket.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * ClassName:    Consumer
 * Package:    com.jp.platform.saas.message.rocketmq.util
 * Description: 消息消费者
 * Datetime:    2025/3/13   15:15
 * Author:   朱殿辉
 */
@Slf4j
public class Consumer {

    //消费者
    private DefaultMQPushConsumer consumer;

    private boolean isConsumerStarted = false;

    //注入
    public Consumer(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;
    }

    public Consumer(String nameServerAddr, String groupName) {
        consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(nameServerAddr);
    }

    /**
     * 注册监听器 针对不同的消息必须创建一个新对象
     *  消费消息 只需要启动一次
     * @param listener
     * @param topic
     * @param tag
     * @throws MQClientException
     */
    public void registerMessageListener(MessageListenerConcurrently listener, String topic, String tag) throws MQClientException{
        log.info("注册消息监听器开始：{},{}",topic,tag);
        consumer.subscribe(topic, tag);
        consumer.registerMessageListener(listener);
        log.info("注册消息监听器成功：{},{}",topic,tag);
    }

    /**
     *  启动消费者
     *  有消息进来自动消费
     * @throws MQClientException
     */
    public void startConsumer() throws MQClientException{
        log.info("消费者启动开始");
        if (!isConsumerStarted) {
            consumer.start();
            isConsumerStarted = true;
        }
        log.info("消费者启动成功");
    }


    public void shutdownConsumer() {
        consumer.shutdown();
    }
}
