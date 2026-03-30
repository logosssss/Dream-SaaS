package com.zhu.saas.gateway.filter.abs;

import com.zhu.saas.common.constant.Response;
import com.zhu.saas.gateway.filter.abs.global.AbstractGlobalFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName:    CustomErrorFilter
 * Package:    com.zhu.gateway.filter
 * Description: 错误过滤器
 * Datetime:    2024/11/30   15:07
 * Author:   朱殿辉
 */
@Component
@Slf4j
public class CustomErrorFilter extends AbstractGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .onErrorResume(e -> processError(exchange, e));
    }

    /**
     * 处理503服务找不到错误
     * @param exchange
     * @param e
     * @return
     */
    private Mono<Void> processError(ServerWebExchange exchange, Throwable e) {
        log.error("报错信息：", e);
        if (e instanceof NotFoundException) {
            Response responseBase = new Response(503, "服务繁忙，请稍后重试");
            return responseJson(responseBase, exchange);
        }
        Response responseBase = new Response(500, "系统繁忙，请稍后重试");
        return responseJson(responseBase, exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
