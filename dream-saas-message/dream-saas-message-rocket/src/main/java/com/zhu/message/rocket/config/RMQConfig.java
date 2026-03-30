package com.zhu.message.rocket.config;//package com.jp.platform.saas.message.rocketmq.config;
//
//import com.jp.platform.saas.message.rocketmq.util.Producer;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * ClassName:    RocketMQConfig
// * Package:    com.jp.platform.saas.message.rocketmq.config
// * Description: 消息数据配置类
// * Datetime:    2025/3/13   15:29
// * Author:   朱殿辉
// */
//@Configuration
//@ConfigurationProperties(prefix = "rocketmqconfig")
//public class RMQConfig {
//
//    private String nameServerAddr;
//    private String producerGroup;
//
//    public String getNameServerAddr() {
//        return nameServerAddr;
//    }
//
//    public void setNameServerAddr(String nameServerAddr) {
//        this.nameServerAddr = nameServerAddr;
//    }
//
//    public String getProducerGroup() {
//        return producerGroup;
//    }
//
//    public void setProducerGroup(String producerGroup) {
//        this.producerGroup = producerGroup;
//    }
//
//    @Bean
//    public DefaultMQProducer rocketMQProducer() throws MQClientException {
//        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
//        producer.setNamesrvAddr(nameServerAddr);
//        producer.start();
//        return producer;
//    }
//
//    @Bean
//    public Producer rocketMQProducerUtil(DefaultMQProducer producer) {
//        return new Producer(producer);
//    }
//}
