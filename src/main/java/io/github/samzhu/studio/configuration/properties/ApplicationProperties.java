package io.github.samzhu.studio.configuration.properties;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Configuration properties for the studio application. This class holds the
 * configuration for various model types including LLM, Embedding, Audio, and
 * Image models.
 */
@Data
@Component
@ConfigurationProperties(prefix = "studio")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ApplicationProperties {
    DefaultModelSetting defaultModelSetting;
    List<LlmModelProperties> llmModels;
    List<EmbeddingModelProperties> embeddingModels;
    List<AudioModelProperties> audioModels;
    List<ImageModelProperties> imageModels;

    /**
     * Finds an LLM model by its ID.
     *
     * @param id The ID of the LLM model to find.
     * @return An Optional containing the LlmModelProperties if found, or an empty
     *         Optional if not found.
     */
    public Optional<LlmModelProperties> findLlmModelById(String id) {
        return llmModels.stream().filter(model -> model.getId().equals(id)).findFirst();
    }

    /**
     * Finds an Embedding model by its ID.
     *
     * @param id The ID of the Embedding model to find.
     * @return An Optional containing the EmbeddingModelProperties if found, or an
     *         empty Optional if not found.
     */
    public Optional<EmbeddingModelProperties> findEmbeddingModelById(String id) {
        return embeddingModels.stream().filter(model -> model.getId().equals(id)).findFirst();
    }

    /**
     * Finds an Audio model by its ID.
     *
     * @param id The ID of the Audio model to find.
     * @return An Optional containing the AudioModelProperties if found, or an empty
     *         Optional if not found.
     */
    public Optional<AudioModelProperties> findAudioModelById(String id) {
        return audioModels.stream().filter(model -> model.getId().equals(id)).findFirst();
    }

    /**
     * Finds an Image model by its ID.
     *
     * @param id The ID of the Image model to find.
     * @return An Optional containing the ImageModelProperties if found, or an empty
     *         Optional if not found.
     */
    public Optional<ImageModelProperties> findImageModelById(String id) {
        return imageModels.stream().filter(model -> model.getId().equals(id)).findFirst();
    }
}
