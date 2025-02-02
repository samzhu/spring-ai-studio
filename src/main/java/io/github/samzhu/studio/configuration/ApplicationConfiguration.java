package io.github.samzhu.studio.configuration;

import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationConfiguration {
    
}
