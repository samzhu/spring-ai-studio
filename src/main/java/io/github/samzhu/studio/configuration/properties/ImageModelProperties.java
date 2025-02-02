package io.github.samzhu.studio.configuration.properties;

import lombok.Getter;
import lombok.Setter;

/** Configuration properties for an Image model. */
@Setter
@Getter
public class ImageModelProperties {
  /** Unique identifier for the model */
  private String id;

  /** Name of the model */
  private String name;

  /** Description of the model */
  private String description;

  /** Base URL for API calls */
  private String baseUrl;

  /** API key for authentication */
  private String apikey;
}
