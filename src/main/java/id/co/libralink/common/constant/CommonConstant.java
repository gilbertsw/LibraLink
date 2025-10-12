package id.co.libralink.common.constant;

import java.util.Locale;

public final class CommonConstant {

    private CommonConstant() {
        // can't instantiate
    }

    // Entity related
    private static final String ID = "id";
    private static final String CREATED_BY = "createdBy";
    private static final String CREATED_DATE = "createdDate";
    private static final String UPDATED_BY = "updatedBy";
    private static final String UPDATED_DATE = "updatedDate";

    public static final String[] DEFAULT_IGNORE_PROPERTIES = {
            ID, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,
    };

    public static final Locale[] SUPPORTED_LOCALE = {
            Locale.of("en", "US"),
            Locale.of("in", "ID")
    };

}
