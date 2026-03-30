package com.zhu.saas.gateway.filter.abs;

import com.zhu.saas.common.constant.Response;
import com.zhu.saas.gateway.constant.OrderConstants;
import com.zhu.saas.gateway.filter.abs.global.AbstractGlobalFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @className: AuthFilterAbs
 * @package: com.zhu.gateway.filter.abs
 * @description: 鉴权过滤器抽象类
 * @author: 朱殿辉
 */
@Slf4j
public abstract class AbstractAuthFilter extends AbstractGlobalFilter implements GlobalFilter, Ordered {


    /**
     * 过滤执行顺序，越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return OrderConstants.ORDER_5;
    }

    /**
     * 过滤器逻辑执行方法
     * @param exchange 具体处理对象
     * @param chain 过滤链路
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Response<ServerWebExchange> responseBase = handle(exchange);
        if (0 == responseBase.code) {
            return chain.filter(responseBase.data);
        }
        return responseJson(responseBase, exchange);
    }
}
