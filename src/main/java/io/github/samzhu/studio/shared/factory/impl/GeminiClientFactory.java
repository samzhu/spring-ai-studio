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

/**
 * Google Gemini LLM 客戶端工廠實現類。
 * 負責創建和配置與 Google Gemini API 通信的客戶端實例。
 * 
 * <p>
 * 此工廠類實現了 {@link LlmClientFactory} 接口，專門用於處理 Google Gemini 模型的客戶端創建。
 * 它使用 Spring AI 的 OpenAI 客戶端適配器來與 Gemini API 進行通信。
 * </p>
 * 
 * <p>
 * 主要功能：
 * </p>
 * <ul>
 * <li>創建配置完善的 Gemini 聊天客戶端</li>
 * <li>處理 API 認證</li>
 * <li>配置模型參數</li>
 * </ul>
 *
 * @author samzhu
 * @since 2025-02-14
 * @see LlmClientFactory
 * @see ChatClient
 */

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

    /**
     * 根據提供的屬性創建 Gemini 聊天客戶端。
     *
     * @param properties 包含必要配置的模型屬性，必須包含 API 金鑰
     * @return 配置完成的聊天客戶端實例
     * @throws IllegalArgumentException 當未提供 API 金鑰時拋出
     */
    @Override
    public ChatClient createClient(LlmModelProperties properties) {
        log.info("開始創建 Gemini 聊天客戶端");

        // 從屬性中獲取 API 金鑰
        String apiKey = properties.getProperties().get("api-key");
        if (apiKey == null || apiKey.isEmpty()) {
            log.error("未提供 Gemini API 金鑰");
            throw new IllegalArgumentException("Gemini API key must be provided in model properties");
        }

        // 創建 OpenAiApi 實例
        log.debug("正在創建 OpenAiApi 實例，使用基礎 URL: {}", BASE_URL);
        var openAiApi = OpenAiApi.builder()
                .baseUrl(BASE_URL)
                .apiKey(apiKey)
                .completionsPath(COMPLETIONS_PATH)
                .embeddingsPath(EMBEDDINGS_PATH)
                .restClientBuilder(restClientBuilder)
                .webClientBuilder(webClientBuilder)
                .responseErrorHandler(RetryUtils.DEFAULT_RESPONSE_ERROR_HANDLER)
                .build();

        // 配置 Chat 選項
        log.debug("正在配置聊天選項，使用模型: {}", properties.getModel());
        var openAiChatOptions = OpenAiChatOptions.builder()
                .model(properties.getModel()) // 使用配置的模型名稱
                .build();

        // 創建 ChatModel
        log.debug("正在創建聊天模型");
        var chatModel = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(openAiChatOptions)
                .retryTemplate(RetryUtils.DEFAULT_RETRY_TEMPLATE)
                .build();

        // 返回 ChatClient 實例
        log.debug("Gemini 聊天客戶端創建完成");
        return ChatClient.create(chatModel);
    }

    /**
     * 獲取此工廠支援的 LLM 提供商類型。
     *
     * @return 返回 GOOGLE_GEMINI 提供商類型
     */
    @Override
    public LlmProvider getProvider() {
        return LlmProvider.GOOGLE_GEMINI;
    }
}