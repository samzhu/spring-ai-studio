package io.github.samzhu.studio.configuration.properties;

import java.util.HashMap;
import java.util.Map;

import io.github.samzhu.studio.constants.LlmProvider;
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

   

    /**
     * Specifies the LLM client type (e.g. "openai", "anthropic")
     * Used to determine which client factory to use for model instantiation
     * 
     * Example values:
     * - "openai" for OpenAI models (GPT-4, GPT-3.5)
     * - "anthropic" for Anthropic models (Claude)
     */
    LlmProvider llmProvider;

    String model;

     /**
	 * Additional native properties to set on the LLM provider.
	 */
    Map<String, String> properties = new HashMap<>();
}
