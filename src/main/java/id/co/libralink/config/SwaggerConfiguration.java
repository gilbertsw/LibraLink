package id.co.libralink.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfiguration {

    @Value("${endpoint.url.v1}${endpoint.admin.base}/**")
    private String adminPath;

    @Value("${endpoint.url.v1}${endpoint.user.base}/**")
    private String userPath;

    @Value("${server.url}")
    private String serverUrl;

    @Bean
    public GroupedOpenApi commonGroup() {
        return GroupedOpenApi.builder()
                .group("Common").pathsToExclude(adminPath, userPath).build();
    }

    @Bean
    public GroupedOpenApi adminGroup() {
        return GroupedOpenApi.builder()
                .group("Admin Dashboard").pathsToMatch(adminPath).build();
    }

    @Bean
    public GroupedOpenApi userGroup() {
        return GroupedOpenApi.builder()
                .group("User Dashboard").pathsToMatch(userPath).build();
    }

    @Bean
    public OpenAPI apiInfo() {
        final String securitySchemeName = "Bearer Authentication";
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url(serverUrl)
                                .description("Server URL in Local environment")))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .info(
                        new Info()
                                .title("LibraLink APIs")
                                .description("REST APIs for LibraLink application")
                                .version("1.0"));
    }

}
