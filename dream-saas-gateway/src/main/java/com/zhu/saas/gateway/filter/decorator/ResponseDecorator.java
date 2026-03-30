package com.zhu.saas.gateway.filter.decorator;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.zhu.saas.common.constant.Response;
import com.zhu.saas.gateway.config.properties.LoginUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @className: ResponseDecorator
 * @package: com.zhu.gateway.decorator
 * @description: 响应数据处理
 * @projectName: IntelliJ IDEA
 * @author: 朱殿辉
 */
@Slf4j
public class ResponseDecorator extends ServerHttpResponseDecorator {

    private LoginUrlConfig loginUrlConfig;

    private final ServerHttpResponse serverHttpResponse;

    private final ServerWebExchange exchange;

    private final long MINUTE_30 = 1800000;


    private static final Set<MediaType> FILE_MEDIA_TYPES = new HashSet<>();

    static {
        FILE_MEDIA_TYPES.add(MediaType.APPLICATION_OCTET_STREAM);
        FILE_MEDIA_TYPES.add(MediaType.parseMediaType("application/zip"));
        FILE_MEDIA_TYPES.add(MediaType.parseMediaType("application/force-download;charset=UTF-8"));
    }

    public ResponseDecorator(ServerHttpResponse delegate,ServerWebExchange exchange) {
        super(delegate);
        this.serverHttpResponse = delegate;
        this.exchange = exchange;
        this.loginUrlConfig = SpringUtil.getBean(LoginUrlConfig.class);
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
            if (body instanceof Flux) {
                Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                String requestPath = exchange.getRequest().getURI().getPath();
                HttpHeaders headers = exchange.getResponse().getHeaders();
                // 检查响应头中的 Content-Type 是否为文件类型
                boolean isFileResponse = isFileResponse(headers);
                //文件流直接返回
                if(isFileResponse){
                    return super.writeWith(fluxBody);
                }

                return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                    DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                    DataBuffer join = dataBufferFactory.join(dataBuffers);
                    byte[] content = new byte[join.readableByteCount()];
                    join.read(content);
                    DataBufferUtils.release(join);
                    String bodyStr = new String(content, StandardCharsets.UTF_8);
                    Response responseBase;
                    if (JSONUtil.isJson(bodyStr)) {
                        responseBase = JSONObject.parseObject(bodyStr, Response.class);
                    } else {
                        responseBase = new Response(bodyStr);
                    }
                    if (0 == responseBase.code && checkUrl(requestPath)) {
                        bodyStr = setToken(responseBase);
                    }
                    getDelegate().getHeaders().setContentLength(bodyStr.getBytes(StandardCharsets.UTF_8).length);
                    return bufferFactory().wrap(bodyStr.getBytes(StandardCharsets.UTF_8));
                }));
            }
        return super.writeWith(body);
    }

    private String modifyBody(String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        jsonObject.put("token", "修改后数据");
        return JSONObject.toJSONString(jsonObject);
    }

    /**
     * 判断是否文件数据类型
     * @param headers 响应头
     * @return 是否为文件数据类型
     */
    public boolean isFileResponse(HttpHeaders headers) {
        if (headers == null || headers.getContentType() == null) {
            return false;
        }

        MediaType contentType = headers.getContentType();
        log.info("Content-Type: {}", contentType);

        return FILE_MEDIA_TYPES.contains(contentType);
    }

    private boolean checkUrl(String url){
        return loginUrlConfig.getLoginUrls().contains(url);
    }

    /**
     * 设置token
     *
     * @param responseBase
     */
    private String setToken(Response responseBase) {
//        if(ObjectUtil.isNotNull(responseBase) && ObjectUtil.isNotNull(responseBase.data)){
//            TokenUserBO tokenUserBO = JSONUtil.toBean(JSONUtil.toJsonStr(responseBase.data) ,TokenUserBO.class); ;
//            if (StringUtils.isNotBlank(tokenUserBO.getUserCode())) {
//                String token = SpringUtil.getBean(JwtTokenService.class).generatorToken(tokenUserBO.getUserCode(), MINUTE_30);
//                tokenUserBO.setToken(token);
//                responseBase.setData(tokenUserBO);
//            }
//        }
        return JSONUtil.toJsonStr(responseBase);
    }
}
