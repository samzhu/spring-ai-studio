package io.github.samzhu.studio.configuration.properties;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Configuration properties for an LLM (Large Language Model) model.
 */
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class LlmModelProperties {
    /** Unique identifier for the model */
    String id;

    /** Name of the model */
    String name;

    /** Description of the model */
    String description;

    /** Base URL for API calls */
    String baseUrl;

    /** Specific model identifier */
    String model;

    /** API key for authentication */
    String apikey;

    /**
     * Used to store specific settings for each model, e.g. projectId and location are Google-specific settings.
     */
    Map<String, String> properties = new HashMap<>();

    /**
     * Specifies the LLM client type (e.g. "openai", "anthropic")
     * Used to determine which client factory to use for model instantiation
     * 
     * Example values:
     * - "openai" for OpenAI models (GPT-4, GPT-3.5)
     * - "anthropic" for Anthropic models (Claude)
     */
    String clientType;
}
