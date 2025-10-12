package id.co.libralink.common.message.impl;

import id.co.libralink.common.message.MessageSourceService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageSourceServiceImpl implements MessageSourceService {

    private final MessageSource messageSource;

    public MessageSourceServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getLocalizedMessage(final String key) {
        return this.getLocalizedMessage(key, (Object[]) null);
    }

    @Override
    public String getLocalizedMessage(final String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.getLocalizedMessage(key, args, locale);
    }

    @Override
    public String getLocalizedMessage(final String key, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.getLocalizedMessage(key, args, locale);
    }

    @Override
    public String getLocalizedMessage(final String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }

    @Override
    public String getLocalizedMessage(final String key, Object[] args, final String defaultMessage, Locale locale) {
        return messageSource.getMessage(key, args, defaultMessage, locale);
    }

    @Override
    public String getLocalizedMessage(final String key, final String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, defaultMessage, locale);
    }
}
