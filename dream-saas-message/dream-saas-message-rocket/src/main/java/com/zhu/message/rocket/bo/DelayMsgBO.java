package com.zhu.message.rocket.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName:    DelayMsgBO
 * Package:    com.jp.platform.saas.common.bo
 * Description:
 * Datetime:    2025/3/7   15:52
 * Author:   朱殿辉
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DelayMsgBO<T> extends MsgBO<T>{

    /** 延迟级别 4.0版本*/
    private DelayLevelEnum delayLevel;

    /** 延迟时间 单位：秒 5.0版本*/
    private long second;
}
