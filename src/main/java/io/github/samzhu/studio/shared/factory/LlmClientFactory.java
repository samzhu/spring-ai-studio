package io.github.samzhu.studio.shared.factory;

import org.springframework.ai.chat.client.ChatClient;

import io.github.samzhu.studio.configuration.properties.LlmModelProperties;
import io.github.samzhu.studio.constants.LlmProvider;

public interface LlmClientFactory {
    /**
     * 根據提供的屬性建立聊天客戶端
     * @param properties LLM 模型屬性
     * @return 聊天客戶端實例
     */
    ChatClient createClient(LlmModelProperties properties);

    /**
     * 取得 LLM 提供商
     * @return LLM 提供商類型
     */
    LlmProvider getProvider();
}
