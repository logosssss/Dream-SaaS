package com.zhu.message.rocket.constants;


/**
 * ClassName:    Constants
 * Package:    com.jp.platform.saas.message.rocketmq.constants
 * Description:
 * Datetime:    2025/4/18   14:41
 * Author:   朱殿辉
 */

public class MsgConstants {
    /** 业务id*/
    public static final String BUSINESS_ID = "businessId";

    // 扣库存消息组
    public static final String DEDUCT_INVENTORY_GROUP = "DEDUCT_INVENTORY_GROUP";

    /** 扣库存*/
    public static final String DEDUCT_INVENTORY_TOPIC = "deductInventoryTopic";



    // 退还库存消息组
    public static final String RETURN_INVENTORY_GROUP = "RETURN_INVENTORY_GROUP";

    /** 退库存*/
    public static final String RETURN_INVENTORY_TOPIC = "returnInventoryTopic";


    //取消订消息组
    public static final String CANCEL_ORDER_GROUP = "CANCEL_ORDER_GROUP";

    /** 取消订单*/
    public static final String CANCEL_ORDER_TOPIC = "cancelOrderTopic";


    //菜单访问
    public static final String MENU_VISIT_GROUP = "MENU_VISIT_GROUP";
    public static final String MENU_VISIT_TOPIC = "menuVisitTopic";

    /** CRM渠道状态变更*/
    public static final String CRM_CHANNEL_STATE_GROUP = "CRM_CHANNEL_STATE_GROUP";
    /** CRM渠道状态变更 */
    public static final String CRM_CHANNEL_STATE_TOPIC = "crmChannelStateTopic";
    /**　开始状态变更*/
    public static final String CRM_CHANNEL_STATE_START_TAG = "startStatusChange";
    /*×　结束状态变更*/
    public static final String CRM_CHANNEL_STATE_END_TAG = "endStatusChange";

    /** 拼接符号*/
    public static final String JOIN_SYMBOL = " || ";

    /** CRM渠道状态变更 统一tag*/
    public static final String CRM_CHANNEL_STATE_TAG = CRM_CHANNEL_STATE_START_TAG + JOIN_SYMBOL + CRM_CHANNEL_STATE_END_TAG;

    /** CRM渠道统计数据*/
    public static final String CRM_STATISTICS_GROUP = "CRM_STATISTICS_GROUP";
    /** CRM渠道统计数据*/
    public static final String CRM_STATISTICS_TOPIC = "crmStatisticsTopic";
    /** 增加线索：业务id：线索id*/
    public static final String CRM_STATISTICS_ADD_TAG = "add";
    /** 绑定用户：业务id：用户id*/
    public static final String CRM_STATISTICS_BIND_TAG = "bind";
    /** 用户下单：业务id：订单id*/
    public static final String CRM_STATISTICS_ORDER_TAG = "order";
    /**CRM渠道统计数据 统一tag*/
    public static final String CRM_STATISTICS_TAG = CRM_STATISTICS_ADD_TAG +JOIN_SYMBOL + CRM_STATISTICS_BIND_TAG + JOIN_SYMBOL + CRM_STATISTICS_ORDER_TAG;

    /** 系统消息*/
    public static final String MSG_SYS_BIZ_GROUP = "MSG_SYS_BIZ_GROUP";
    public static final String MSG_SYS_BIZ_TOPIC = "msgSysBizTopic";
    public static final String MSG_SYS_BIZ_TAG = MsgBizTriggerEnum.PAY_PASS.getCode()
            +JOIN_SYMBOL + MsgBizTriggerEnum.PAY_REJECT.getCode() + JOIN_SYMBOL
            + MsgBizTriggerEnum.PAY_SUBMIT.getCode() + JOIN_SYMBOL + MsgBizTriggerEnum.PAY_WAIT.getCode();



}
