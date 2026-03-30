package com.zhu.message.rocket.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * ClassName:    RocketMQProducerSingleton
 * Package:    com.jp.platform.saas.message.rocketmq.util
 * Description: 单例创建生产者
 * Datetime:    2025/3/13   18:03
 * Author:   朱殿辉
 */
@Slf4j
public class RocketMQProducerSingleton {
    private static volatile Producer producer = null;

    private RocketMQProducerSingleton() {
    }

    public static Producer getInstance(String nameServerAddr, String producerGroupName) {
        if (producer == null) {
            synchronized (RocketMQProducerSingleton.class) {
                if (producer == null) {
                    try {
                        log.info("nameServerAddr：{}",nameServerAddr);
                        log.info("producerGroupName：{}",producerGroupName);
                        producer = new Producer(nameServerAddr, producerGroupName);
                    } catch (MQClientException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return producer;
    }

    public static void shutdownProducer() {
        producer.shutdownProducer();
    }
}
