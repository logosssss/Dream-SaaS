package com.zhu.message.rocket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName:    MsgBizType
 * Package:    com.jp.platform.saas.msg.constant
 * Description: 消息业务类型
 * Datetime:    2025/11/12   10:28
 * Author:   朱殿辉
 */
@Getter
@AllArgsConstructor
public enum MsgBizTriggerEnum {
    /** 待付款*/
    PAY_WAIT("PAY_WAIT","待付款"),

    /**付款成功 */
    PAY_SUCCESS("PAY_SUCCESS","付款成功"),

    /**支付提交 */
    PAY_SUBMIT("PAY_SUBMIT","支付提交"),

    /**支付通过*/
    PAY_PASS("PAY_PASS","支付通过"),

    /**支付驳回 */
    PAY_REJECT("PAY_REJECT","支付驳回")

    ;


    private final String code;
    private final String desc;

    public static MsgBizTriggerEnum of(String code) {
        for (MsgBizTriggerEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
