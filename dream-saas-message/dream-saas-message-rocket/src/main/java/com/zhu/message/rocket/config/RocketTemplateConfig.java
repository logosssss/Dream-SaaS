package com.zhu.message.rocket.config;

import com.zhu.message.rocket.constants.MsgConstants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:    RocketTemplateConfig
 * Package:    com.jp.platform.saas.message.rocketmq.config
 * Description: 不同的生产组自定义配置
 * Datetime:    2025/3/28   16:34
 * Author:   朱殿辉
 */
@Configuration
public class RocketTemplateConfig {

    @Autowired
    private RocketMQConfig rocketMQConfig;

    @Bean("adminWrapMQTemplate")
    public RocketMQTemplate menuVisitMQTemplate() {
        RocketMQTemplate template = new RocketMQTemplate();
        DefaultMQProducer producer = new DefaultMQProducer(MsgConstants.MENU_VISIT_GROUP);
        producer.setNamesrvAddr(rocketMQConfig.getNameServer());
        template.setProducer(producer);
        return template;
    }

}
