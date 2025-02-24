package org.example.teamwork.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// TODO 无法访问,不知道为什么
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info());
    }

    public Info info() {
        return new Info()
                .title("一个用来测试的项目")
                .description("第一步: 实现用户的增删改查")
                .version("0");
    }
}
