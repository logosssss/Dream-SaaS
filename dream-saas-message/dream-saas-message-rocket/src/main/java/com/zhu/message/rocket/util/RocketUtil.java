package com.zhu.message.rocket.util;//package com.jp.platform.saas.message.rocketmq.util;
//
//import com.jp.framework.core.common.Response;
//import com.jp.framework.core.util.Assert;
//import com.jp.framework.core.util.JsonUtils;
//import com.jp.platform.saas.message.rocketmq.bo.DelayMsgBO;
//import com.jp.platform.saas.message.rocketmq.bo.MsgBO;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//
///**
// * @className: RocketUtil
// * @package: com.zhu.msg.rocket
// * @description: RocketMQ 消息发送工具类型：简单功能实现
// * @projectName: IntelliJ IDEA
// * @author: 朱殿辉
// */
//@Slf4j
//@Component
//public class RocketUtil {
//
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//
//    private static final String errorMsgStart = "主题：{},RocketMQ发送消息开始，消息体：{}";
//
//    private static final String errorMsgEnd = "主题：{},RocketMQ发送消息开始，消息体：{}";
//
//    private static final String errorResponse = "消息发送失败，异常信息：";
//
//
//    /**
//     * 同步消息发送
//     *
//     * @param msgBO
//     * @return
//     */
//    public Response sendSyncMessage(MsgBO msgBO) {
//        checkParam(msgBO);
//        try {
//            log.info(errorMsgStart, msgBO.getTopic(), msgBO.getBody());
//            // 同步发送消息
//            SendResult sendResult = rocketMQTemplate.syncSend(msgBO.getTopic(), msgBO.getBody());
//            log.info("RocketMQ发送消息结束，结果：{}", sendResult);
//            return new Response(sendResult);
//        } catch (Exception e) {
//            log.error(errorMsgEnd, msgBO.getTopic(), e);
//            return Response.error(errorResponse + e.getMessage());
//        }
//    }
//
//    /**
//     * 异步消息发送
//     *
//     * @param msgBO
//     * @return
//     */
//    public Response sendAsync(MsgBO msgBO) {
//        checkParam(msgBO);
//        try {
//            log.info(errorMsgStart, msgBO.getTopic(), msgBO.getBody());
//            // 异步发送消息
//            rocketMQTemplate.convertAndSend(msgBO.getTopic(), msgBO.getBody());
//            log.info("RocketMQ异步发送消息结束");
//            return new Response("消息发送成功");
//        } catch (Exception e) {
//            log.error(errorMsgEnd, msgBO.getTopic(), e);
//            return Response.error(errorResponse + e.getMessage());
//        }
//    }
//
//
//    /**
//     * 延迟消息
//     * 5.0 版本支持直接自定义时间
//     * @param msgBO
//     * @return
//     */
//    public Response sendDelay(DelayMsgBO msgBO) {
//        checkParam(msgBO);
//        try {
//            Assert.notNull(msgBO.getSecond(), "延迟时间不能为空");
//            log.info(errorMsgStart, msgBO.getTopic(), msgBO.getBody());
//            Message<String> message = MessageBuilder.withPayload(JsonUtils.pojoToJson(msgBO.getBody())).build();
//            String topic = msgBO.getTopic();
//            SendResult sendResult = rocketMQTemplate.syncSendDelayTimeSeconds(topic, message, msgBO.getSecond());
//            log.info("延迟消息发送结果: " + sendResult.getMsgId());
//
//            return new Response(sendResult);
//        } catch (Exception e) {
//            log.error(errorMsgEnd, msgBO.getTopic(), e);
//            return Response.error(errorResponse + e.getMessage());
//        }
//    }
//
//    /**
//     * 5.0以前版本只能通过延迟级别发送
//     * 延迟消息发送
//     *
//     * @param msgBO
//     * @return
//     */
//    public Response sendDelayOld(DelayMsgBO msgBO) {
//        checkParam(msgBO);
//        try {
//            Assert.notNull(msgBO.getDelayLevel(), "延迟级别不能为空");
//            log.info(errorMsgStart, msgBO.getTopic(), msgBO.getBody());
//            Message<String> msg = MessageBuilder.withPayload(JsonUtils.pojoToJson(msgBO.getBody()))
//                    .setHeader(MessageHeaders.CONTENT_TYPE, "text/plain")
//                    .build();
//            SendResult sendResult = rocketMQTemplate.syncSend(msgBO.getTopic(), msg, 5000,
//                    msgBO.getDelayLevel().getLevel());
//            log.info("延迟消息发送返回的结果:{}", sendResult);
//            return new Response(sendResult);
//        } catch (Exception e) {
//            log.error(errorMsgEnd, msgBO.getTopic(), e);
//            return Response.error(errorResponse + e.getMessage());
//        }
//    }
//
//
//    /**
//     * 参数校验
//     *
//     * @param msgBO 消息对象
//     */
//    private void checkParam(MsgBO msgBO) {
//        Assert.notNull(msgBO, "消息对象不能为空");
//        Assert.notNull(msgBO.getTopic(), "消息主题不能为空");
//        Assert.notNull(msgBO.getBody(), "消息体不能为空");
//    }
//
//}
