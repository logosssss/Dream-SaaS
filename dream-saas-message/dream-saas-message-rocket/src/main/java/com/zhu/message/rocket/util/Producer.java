package com.zhu.message.rocket.util;

import cn.hutool.core.util.ObjectUtil;
import com.zhu.message.rocket.bo.DelayLevelEnum;
import com.zhu.message.rocket.constants.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * ClassName:    Producer
 * Package:    com.jp.platform.saas.message
 * Description: 消息生产者
 * Datetime:    2025/3/13   15:10
 * Author:   朱殿辉
 */
@Slf4j
public class Producer {

    //消费者
    private DefaultMQProducer producer;

    //注入参数
    public Producer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    public Producer(String nameServerAddr,String groupName) throws MQClientException{
        producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(nameServerAddr);
        producer.start();
    }

    /**
     *  发送同步消息
     *  可靠性高，性能低，适用于对可靠性要求高的场景。
     * @param topic
     * @param tag
     * @param body
     * @param businessId 业务id：唯一，做幂等用
     * @return
     * @throws MQClientException
     * @throws RemotingException
     * @throws MQBrokerException
     * @throws InterruptedException
     */
    public SendResult sendSyncMessage(String topic, String tag, String body,String businessId) throws MQClientException, RemotingException, MQBrokerException, InterruptedException{
        log.info("发送同步消息：{},{},{},{}",topic,tag,body,businessId);
        Message msg = createMessage(topic, tag, body,businessId);
        return producer.send(msg);
    }

    /**
     * 发送异步消息
     * 性能高，代码复杂，适用于对性能要求高的场景。
     * @param topic
     * @param tag
     * @param body
     * @param callback
     * @throws Exception
     */
    public void sendAsyncMessage(String topic, String tag, String body, SendCallback callback,String businessId) throws MQClientException, RemotingException, InterruptedException {
        log.info("发送异步消息：{},{},{},{}",topic,tag,body,businessId);
        Message msg = createMessage(topic, tag, body,businessId);
        producer.send(msg, callback);
    }

    /**
     * 发送单向消息
     * 性能最高，不可靠，适用于对可靠性要求不高的场景。
     * @param topic
     * @param tag
     * @param body
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    public void sendOnewayMessage(String topic, String tag, String body,String businessId) throws MQClientException, RemotingException, InterruptedException {
        log.info("发送单向消息：{},{},{},{}",topic,tag,body,businessId);
        Message msg = createMessage(topic, tag, body,businessId);
        producer.sendOneway(msg);
    }

    /**
     * 发送单向消息
     * 性能最高，不可靠，适用于对可靠性要求不高的场景。
     * @param topic
     * @param tag
     * @param body
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    public void sendOnewayMessage(String topic, String tag, String body) throws MQClientException, RemotingException, InterruptedException {
        log.info("发送单向消息：{},{},{}",topic,tag,body);
        Message msg = createMessage(topic, tag, body);
        producer.sendOneway(msg);
    }

    /**
     *
     * 延时消息
     * 定时触发，适用于定时任务和提醒通知。
     * @param topic
     * @param tag
     * @param body
     * @param delayLevel
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    public SendResult sendDelayMessage(String topic, String tag, String body, DelayLevelEnum delayLevel, String businessId) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        log.info("发送延时消息：{},{},{},{},{}",topic,tag,body,delayLevel,businessId);
        Message msg = createMessage(topic, tag, body,businessId);
        msg.setDelayTimeLevel(delayLevel.getLevel());
        return producer.send(msg);
    }

    /**
     *
     * 延时消息
     * 定时触发，适用于定时任务和提醒通知。
     * @param topic
     * @param tag
     * @param body
     * @param second
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    public SendResult sendDelayMessage(String topic, String tag, String body, long second,String businessId) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        log.info("发送延时消息：{},{},{},{},{}",topic,tag,body,second,businessId);
        Message msg = createMessage(topic, tag, body,businessId);
        msg.setDelayTimeSec(second);
        return producer.send(msg);
    }

    /**
     * 事务消息
     * 支持分布式事务，适用于需要事务一致性的场景
     * @param topic
     * @param tag
     * @param body
     * @param transactionListener
     * @throws MQClientException
     */
    public TransactionSendResult sendTransactionMessage(String topic, String tag, String body, TransactionListener transactionListener,String businessId) throws MQClientException {
        log.info("发送事务消息：{},{},{},{}",topic,tag,body,businessId);
        Message msg = createMessage(topic, tag, body,businessId);
        return producer.sendMessageInTransaction(msg, null);
    }

    public void shutdownProducer() {
        producer.shutdown();
    }

    private Message createMessage(String topic, String tag, String body,String businessId) {
        if (ObjectUtil.isAllNotEmpty(topic, tag, body,businessId)) {
            Message message = new Message(topic, tag, body.getBytes());
            message.putUserProperty(MsgConstants.BUSINESS_ID,businessId);
            return message;
        } else {
            throw new IllegalArgumentException("Topic, tag and body cannot be empty");
        }
    }

    private Message createMessage(String topic, String tag, String body) {
        if (ObjectUtil.isAllNotEmpty(topic, tag, body)) {
            Message message = new Message(topic, tag, body.getBytes());
            return message;
        } else {
            throw new IllegalArgumentException("Topic, tag and body cannot be empty");
        }
    }

}
