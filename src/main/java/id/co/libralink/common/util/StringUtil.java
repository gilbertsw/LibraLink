package id.co.libralink.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class StringUtil extends StringUtils {

    private StringUtil() {
        // can't instantiate
    }

    public static String makeCamelCaseToSnakeCase(String fieldName) {
        return Arrays.stream(splitByCharacterTypeCamelCase(fieldName))
                .map(StringUtils::uncapitalize)
                .collect(Collectors.joining("_"));
    }

}
