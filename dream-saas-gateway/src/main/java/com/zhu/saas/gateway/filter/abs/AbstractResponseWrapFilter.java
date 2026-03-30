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
 * @className: AbstractResponseWrapFilter
 * @package: com.zhu.gateway.filter.abs
 * @description: 相应数据处理逻辑过滤器
 * @projectName: IntelliJ IDEA
 * @author: 朱殿辉
 */
@Slf4j
public abstract class AbstractResponseWrapFilter extends AbstractGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        return OrderConstants.ORDER_12;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Response<ServerWebExchange> responseBase = handle(exchange);
        return chain.filter(responseBase.data);
    }
}
