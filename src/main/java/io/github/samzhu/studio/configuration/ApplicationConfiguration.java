package io.github.samzhu.studio.configuration;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;

import io.github.samzhu.studio.configuration.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationConfiguration {

    ApplicationProperties applicationProperties;

    @Bean
    RestClient.Builder restClientBuilder() {
        RestClient.Builder builder = RestClient.builder()
                .requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .requestInterceptor((request, body, execution) -> {
                    log.info("=== Http Request ===");
                    log.info("URL: {} {}", request.getMethod(), request.getURI());
                    log.info("Headers: {}", request.getHeaders());
                    log.info("Body: {}", new String(body, StandardCharsets.UTF_8));
                    ClientHttpResponse response = execution.execute(request, body);
                    log.info("=== Http Response ===");
                    log.info("Status: {}", response.getStatusCode());
                    log.info("Headers: {}", response.getHeaders());
                    log.info("Body: {}", StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
                    return response;
                });
        return builder;
    }

}
