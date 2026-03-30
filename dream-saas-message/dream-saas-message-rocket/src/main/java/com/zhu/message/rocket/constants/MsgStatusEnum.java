package com.zhu.message.rocket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName:    MsgStatusEnum
 * Package:    com.jp.platform.saas.message.rocketmq.constants
 * Description:
 * Datetime:    2025/4/17   17:28
 * Author:   朱殿辉
 */
@Getter
@AllArgsConstructor
public enum MsgStatusEnum {
    /**发送成功 */
    SEND_OK("SEND_OK","发送成功"),
    /**发送失败 */
    SEND_FAIL("SEND_FAIL","发送失败"),
    /** 消费成功*/
    CONSUME_OK("CONSUME_OK","消费成功"),
    /**消费失败 */
    CONSUME_FAIL("CONSUME_FAIL","消费失败"),
    /**消费端业务处理失败*/
    CONSUME_B_FAIL("CONSUME_B_FAIL","消费端业务处理失败")
    ;

    private final String code;
    private final String desc;


}
