package io.github.samzhu.studio.configuration.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/** Configuration properties for an Audio model. */
@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AudioModelProperties {
  /** Unique identifier for the model */
  @NotNull String id;

  /** Name of the model */
  @NotNull String name;

  /** Description of the model */
  @NotNull String description;

  /** Base URL for API calls */
  @NotNull String baseUrl;

  /** API key for authentication */
  @NotNull String apikey;
}
