# 專案建立

## 建立專案

下載 [Spring Initializr](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.4.2&packaging=jar&jvmVersion=21&groupId=io.github.samzhu&artifactId=studio&name=studio&description=Demo%20project%20for%20Spring%20Boot&packageName=io.github.samzhu.studio&dependencies=spring-ai-openai,lombok,devtools,docker-compose,web,data-mongodb,native,configuration-processor,modulith,htmx,oauth2-resource-server,data-jpa,liquibase,postgresql,validation,cache,actuator,sbom-cyclone-dx,otlp-metrics,prometheus,testcontainers,distributed-tracing,cloud-feign,spring-ai-anthropic,cloud-resilience4j,spring-ai-azure-openai,spring-ai-vectordb-mongodb-atlas,spring-ai-ollama,cloud-stream,spring-ai-vectordb-pgvector,spring-ai-stabilityai,spring-ai-pdf-document-reader,spring-ai-tika-document-reader,spring-ai-markdown-document-reader,spring-ai-vertexai-gemini,spring-ai-vertexai-embeddings,data-jdbc)

建立 .vscode/launch.json

```json
{
    "version": "0.2.0",
    "configurations": [

        {
            "type": "java",
            "name": "Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "StudioApplication",
            "request": "launch",
            "mainClass": "io.github.samzhu.studio.StudioApplication",
            "projectName": "spring-ai-studio",
            "env": {
                "spring.profiles.active": "local-env,local"
            }
        },
        {
            "type": "java",
            "name": "TestStudioApplication",
            "request": "launch",
            "mainClass": "io.github.samzhu.studio.TestStudioApplication",
            "projectName": "spring-ai-studio"
        }
    ]
}
```
