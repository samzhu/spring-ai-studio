package io.github.samzhu.studio.configuration.properties;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.experimental.FieldDefaults;

/** Configuration properties for an Embedding model. */
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class EmbeddingModelProperties {
  /** Unique identifier for the model */
  private String id;

  /** Name of the model */
  private String name;

  /** Description of the model */
  private String description;

  /** Base URL for API calls */
  private String baseUrl;

  /** Specific model identifier */
  private String model;

  /** API key for authentication */
  private String apikey;

  Map<String, String> properties = new HashMap<>();
}
