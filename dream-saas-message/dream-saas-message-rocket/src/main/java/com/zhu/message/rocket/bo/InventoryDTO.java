package com.zhu.message.rocket.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ClassName:    DeductInventoryDTO
 * Package:    com.jp.platform.saas.message.rocketmq.bo
 * Description:
 * Datetime:    2025/4/11   16:25
 * Author:   朱殿辉
 */
@Data
@Builder
@AllArgsConstructor
public class InventoryDTO {

    /** 商品id*/
    private String productId;
    /** skuId*/
    private String skuId;
    /** 商品数量*/
    private Integer num;
    /** 订单id*/
    private String orderId;

    private String orderItemId;
}
