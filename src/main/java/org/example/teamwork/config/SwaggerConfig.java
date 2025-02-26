package org.example.teamwork.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    /*接口文档配置类*/
    @Bean
    public OpenAPI openAPI() {
        // 这里是出口
        return new OpenAPI()
                .info(info());
    }

    public Info info() {
        // 这里写文档

        String title = "个人博客网站接口测试";

        String description = "description";

        Contact contact = new Contact()
                .name("雷乐涔, 叶江涛")
                .url("<UNK>")
                .email("3095171781@qq.com");


        return new Info()
                .title(title)
                .description(description)
                .version("0")
                .contact(contact)
                ;
    }
}
