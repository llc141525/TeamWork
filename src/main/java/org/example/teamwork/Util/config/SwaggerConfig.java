package org.example.teamwork.Util.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("电商系统 API")
                        .version("1.0")
                        .description("接口文档示例")
                        .contact(new Contact().name("开发团队"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("详细说明")
                        .url("https://example.com/docs")
                );
    }
}
