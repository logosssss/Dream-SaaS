package com.zhu.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 同时注册 Spring AI（OpenAI 兼容）与 Spring AI Alibaba（DashScope）两套 {@link ChatClient}，
 * 避免多 {@link ChatModel} Bean 注入歧义。
 */
@Configuration
public class AiChatClientConfig {

    @Bean(name = "openAiChatClient")
    public ChatClient openAiChatClient(@Qualifier("openAiChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

    @Bean(name = "dashScopeChatClient")
    public ChatClient dashScopeChatClient(@Qualifier("dashscopeChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
}
