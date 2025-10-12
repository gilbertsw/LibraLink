package id.co.libralink.config;

import id.co.libralink.common.message.MessageSourceService;
import id.co.libralink.common.message.impl.MessageSourceServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class MessageSourceConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/errors","classpath:i18n/success");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public MessageSourceService messageSourceService(MessageSource messageSource) {
        return new MessageSourceServiceImpl(messageSource);
    }
}
