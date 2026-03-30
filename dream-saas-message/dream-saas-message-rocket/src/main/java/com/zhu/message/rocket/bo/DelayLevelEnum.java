package com.zhu.message.rocket.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName:    DelayLevelEnmu
 * Package:    com.jp.platform.saas.message.rocketmq.bo
 * Description: RocketMQ 延迟消息延迟级别
 * Datetime:    2025/3/7   15:54
 * Author:   朱殿辉
 */
@Getter
@AllArgsConstructor
public enum DelayLevelEnum {
    /** 1秒 */
    ONE_SECOND(1, "1秒"),
    /** 5秒 */
    FIVE_SECONDS(2, "5秒"),
    /** 10秒 */
    TEN_SECONDS(3, "10秒"),
    /** 30秒 */
    THIRTY_SECONDS(4, "30秒"),
    /** 1分钟 */
    ONE_MINUTE(5, "1分钟"),
    /** 2分钟 */
    TWO_MINUTES(6, "2分钟"),
    /** 3分钟 */
    THREE_MINUTES(7, "3分钟"),
    /** 4分钟 */
    FOUR_MINUTES(8, "4分钟"),
    /** 5分钟 */
    FIVE_MINUTES(9, "5分钟"),
    /** 6分钟 */
    SIX_MINUTES(10, "6分钟"),
    /** 7分钟 */
    SEVEN_MINUTES(11, "7分钟"),
    /** 8分钟 */
    EIGHT_MINUTES(12, "8分钟"),
    /** 9分钟 */
    NINE_MINUTES(13, "9分钟"),
    /** 10分钟 */
    TEN_MINUTES(14, "10分钟"),
    /** 20分钟 */
    TWENTY_MINUTES(15, "20分钟"),
    /** 30分钟 */
    THIRTY_MINUTES(16, "30分钟"),
    /** 1小时 */
    ONE_HOUR(17, "1小时"),
    /** 2小时 */
    TWO_HOURS(18, "2小时");

    private final Integer level;   // 延迟级别码
    private final String desc;     // 中文描述

    /**
     * 根据延迟级别码获取对应的枚举实例
     * @param level 延迟级别码
     * @return 对应的枚举实例，如果不存在则返回 null
     */
    public static DelayLevelEnum getLevelByCode(Integer level) {
        if (level == null) {
            return null;
        }
        for (DelayLevelEnum delayLevel : values()) {
            if (delayLevel.getLevel().equals(level)) {
                return delayLevel;
            }
        }
        return null;
    }

    /**
     * 根据延迟级别码获取对应的描述信息
     * @param level 延迟级别码
     * @return 对应的描述信息，如果不存在则返回 null
     */
    public static String getDescriptionByCode(Integer level) {
        DelayLevelEnum delayLevel = getLevelByCode(level);
        return delayLevel != null ? delayLevel.getDesc() : null;
    }
}
