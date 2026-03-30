package com.zhu.message.rocket.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ClassName:    CanCelOrderDTO
 * Package:    com.jp.platform.saas.message.rocketmq.bo
 * Description:
 * Datetime:    2025/4/24   17:54
 * Author:   朱殿辉
 */
@Data
@Builder
@AllArgsConstructor
public class CanCelOrderDTO {
    private String userId;
    private String orderId;
}
