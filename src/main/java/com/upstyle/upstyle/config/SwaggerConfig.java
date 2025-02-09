package com.upstyle.upstyle.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI upstyleAPI() {
        String securitySchemeName = "bearerAuth";  // Swagger에서 인식될 JWT 인증명

        // API 문서 정보
        Info info = new Info()
                .title("UPSTYLE API")
                .description("UPSTYLE API 명세서")
                .version("1.0.0");

        // JWT 인증 방식 설정
        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))  // 서버 설정
                .info(info)  // 문서 정보
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))  // 보안 적용
                .components(new Components().addSecuritySchemes(securitySchemeName, securityScheme));  // 보안 스키마 등록
    }
}