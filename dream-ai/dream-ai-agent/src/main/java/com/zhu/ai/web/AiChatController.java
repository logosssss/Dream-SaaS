package com.zhu.ai.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Optional;

/**
 * 演示 Spring AI（OpenAI 兼容）与 Spring AI Alibaba（DashScope）各一条对话入口。
 */
@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    private final ChatClient openAiChatClient;
    private final ChatClient dashScopeChatClient;

    public AiChatController(
            @Qualifier("openAiChatClient") ChatClient openAiChatClient,
            @Qualifier("dashScopeChatClient") ChatClient dashScopeChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.dashScopeChatClient = dashScopeChatClient;
    }

    /**
     * 使用 OpenAI 兼容通道（由 spring-ai-openai 提供，可指向官方或自建兼容网关）。
     */
    @PostMapping(value = "/openai/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String openAiChat(@RequestBody ChatRequest request) {
        String text = Optional.ofNullable(request.message()).orElse("");
        return openAiChatClient.prompt().user(text).call().content();
    }

    /**
     * 使用阿里云 DashScope（通义等），由 spring-ai-alibaba-starter-dashscope 提供。
     */
    @PostMapping(value = "/dashscope/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String dashScopeChat(@RequestBody ChatRequest request) {
        String text = Optional.ofNullable(request.message()).orElse("");
        return dashScopeChatClient.prompt().user(text).call().content();
    }

    /**
     * DashScope 流式输出（SSE 文本块），便于前端打字机效果。
     */
    @PostMapping(value = "/dashscope/chat/stream", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> dashScopeChatStream(@RequestBody ChatRequest request) {
        String text = Optional.ofNullable(request.message()).orElse("");
        return dashScopeChatClient.prompt().user(text).stream().content();
    }

    public record ChatRequest(String message) {}
}
