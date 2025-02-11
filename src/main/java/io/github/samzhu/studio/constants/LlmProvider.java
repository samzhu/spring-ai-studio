package io.github.samzhu.studio.constants;

/**
 * 代表支援的大型語言模型 (LLM) 供應商及其服務
 */
public enum LlmProvider {
    /**
     * Google Gemini API 服務
     * <p>
     * 包含模型：
     * - Gemini 1.5 Pro
     * - Gemini 1.5 Flash
     * - Gemini 1.0 Pro
     */
    GOOGLE_GEMINI("google-gemini"),

    /**
     * Google Vertex AI 服務
     * <p>
     * 包含模型：
     * - PaLM 2
     * - PaLM 2 Chat
     * - PaLM 2 Code
     */
    GOOGLE_VERTEX_AI("google-vertex-ai"),

    /**
     * OpenAI API 服務
     * <p>
     * 包含模型：
     * - GPT-4
     * - GPT-3.5-turbo
     */
    OPENAI("openai"),

    /**
     * Azure OpenAI 服務
     * <p>
     * 支援 OpenAI 模型的 Azure 託管版本
     */
    AZURE_OPENAI("azure-openai"),

    /**
     * Anthropic Claude API 服務
     * <p>
     * 包含模型：
     * - Claude 3 Opus
     * - Claude 3 Sonnet
     * - Claude 3 Haiku
     */
    ANTHROPIC("anthropic"),

    /**
     * Mistral API 服務
     */
    MISTRAL("mistral"),

    /**
     * Ollama 本地模型服務
     */
    OLLAMA("ollama");

    private final String value;

    LlmProvider(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
