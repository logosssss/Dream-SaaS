package com.zhu.saas.gateway.filter.abs.impl;

import com.zhu.saas.common.constant.Response;
import com.zhu.saas.gateway.filter.abs.AbstractResponseWrapFilter;
import com.zhu.saas.gateway.filter.decorator.ResponseDecorator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @className: ResponseWarpFilterImpl
 * @package: com.zhu.gateway.filter.abs.impl
 * @description: 响应数据
 * @projectName: IntelliJ IDEA
 * @author: 朱殿辉
 */

@Slf4j
@Component
public class ResponseWarpFilterImpl extends AbstractResponseWrapFilter {

    /**
     * 在这里做自定义处理
     * @param exchange
     * @return
     */
    @Override
    protected Response<ServerWebExchange> handle(ServerWebExchange exchange) {
        ResponseDecorator responseDecorator = new ResponseDecorator(exchange.getResponse(),exchange);
        return new Response<>(exchange.mutate().response(responseDecorator).build());
    }
}
