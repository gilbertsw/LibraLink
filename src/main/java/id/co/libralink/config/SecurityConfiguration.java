package id.co.libralink.config;

import id.co.libralink.apigateway.auth.config.WebSecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebSecurityConfiguration.class)
public class SecurityConfiguration {
}
