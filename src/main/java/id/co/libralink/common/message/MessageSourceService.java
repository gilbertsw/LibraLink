package id.co.libralink.common.message;

import java.util.Locale;

public interface MessageSourceService {

    String getLocalizedMessage(String key, Object[] args, Locale locale);

    String getLocalizedMessage(String key, Object[] args, String defaultMessage, Locale locale);

    String getLocalizedMessage(String key, Object[] args);

    String getLocalizedMessage(String key, Object[] args, String defaultMessage);

    String getLocalizedMessage(String key, String defaultMessage);

    String getLocalizedMessage(String key);

}
