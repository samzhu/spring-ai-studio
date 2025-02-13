package io.github.samzhu.studio.shared.factory.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.samzhu.studio.configuration.properties.LlmModelProperties;
import io.github.samzhu.studio.constants.LlmProvider;
import io.github.samzhu.studio.shared.factory.LlmClientFactory;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class GeminiClientFactory implements LlmClientFactory {

    RestClient.Builder restClientBuilder;
    WebClient.Builder webClientBuilder;

    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1beta";
    private static final String COMPLETIONS_PATH = "/openai/chat/completions";
    private static final String EMBEDDINGS_PATH = "/openai/embeddings";

    @Override
    public ChatClient createClient(LlmModelProperties properties) {
        log.debug("Creating Gemini chat client with properties: {}", properties);

        // 從屬性中獲取 API 金鑰
        String apiKey = properties.getProperties().get("api-key");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("Gemini API key must be provided in model properties");
        }

        // 創建 OpenAiApi 實例
        var openAiApi = new OpenAiApi(
                BASE_URL,
                apiKey,
                COMPLETIONS_PATH,
                EMBEDDINGS_PATH,
                restClientBuilder,
                webClientBuilder,
                RetryUtils.DEFAULT_RESPONSE_ERROR_HANDLER);

        // 配置 Chat 選項
        var openAiChatOptions = OpenAiChatOptions.builder()
                .model(properties.getModel()) // 使用配置的模型名稱
                .build();

        // 創建 ChatModel
        var chatModel = new OpenAiChatModel(openAiApi, openAiChatOptions);

        // 返回 ChatClient 實例
        return ChatClient.create(chatModel);
    }

    @Override
    public LlmProvider getProvider() {
        return LlmProvider.GOOGLE_GEMINI;
    }
} 