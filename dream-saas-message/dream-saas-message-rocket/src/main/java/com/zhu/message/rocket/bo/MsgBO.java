package com.zhu.message.rocket.bo;

import lombok.Data;

/**
 * @className: MsgBO
 * @package: com.zhu.common.bean.bo
 * @description:
 * @projectName: IntelliJ IDEA
 * @author: 朱殿辉
 */
@Data
public class MsgBO<T> {
    private Integer msgType;
    private String topic;
    private T body;
}
