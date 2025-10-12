package id.co.libralink.config;

import id.co.libralink.common.audit.UsernameAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorJPAProvider")
public class AuditingConfiguration {

    @Bean
    public AuditorAware<String> auditorJPAProvider() {
        return new UsernameAuditorAware();
    }

}
