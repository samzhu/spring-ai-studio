package io.github.samzhu.studio.configuration.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * System default model IDs
 */
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class DefaultModelSetting {
    /** Default large language model identifier */
    @NotNull
    String llmModelId;

    /** Default embedding model identifier for vector representations */
    @NotNull
    String embeddingModelId;

    /** Default audio processing model identifier */
    @NotNull
    String audioModelId;

    /** Default image processing model identifier */
    @NotNull
    String imageModelId;
}
