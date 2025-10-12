package id.co.libralink.apigateway.security.util;

import id.co.libralink.apigateway.security.enums.SecurityErrorCode;
import id.co.libralink.apigateway.security.model.entity.UserSecurityPolicy;
import id.co.libralink.common.base.code.ErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class PasswordFormatUtil {

    private static final String ONE_MULTIPLE_UPPERCASE_REGEX = "[A-Z]+";
    private static final String ONE_MULTIPLE_LOWERCASE_REGEX = "[a-z]+";
    private static final String ONE_MULTIPLE_NUMBER_REGEX = "\\d+";
    private static final String ONE_MULTIPLE_NON_ALPHANUMERIC_REGEX = "[^a-zA-Z0-9]+";
    private static final String ONLY_ALPHANUMERIC_REGEX = "^[A-Za-z0-9]+$";

    private PasswordFormatUtil() {
    }

    public static List<ErrorCode> validateFormat(String plainPassword, UserSecurityPolicy policy) {
        List<ErrorCode> errorCodes = new ArrayList<>();
        errorCodes.add(assertPasswordLengthValid(plainPassword, policy));
        errorCodes.add(assertUppercasePolicyValid(plainPassword, policy));
        errorCodes.add(assertLowercasePolicyValid(plainPassword, policy));
        errorCodes.add(assertNumberPolicyValid(plainPassword, policy));
        errorCodes.add(assertSpecialCharPolicyValid(plainPassword, policy));
        errorCodes.add(assertOnlyAlphanumeric(plainPassword));

        return errorCodes.stream().filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private static ErrorCode assertPasswordLengthValid(String plainPassword,
        UserSecurityPolicy policy) {
        Integer minLength = policy.getPasswordMinLength();
        boolean isValid = minLength == null || plainPassword.length() >= minLength;
        return createErrorCodeIfInvalid(isValid, SecurityErrorCode.PASSWORD_TOO_SHORT);
    }

    private static ErrorCode assertUppercasePolicyValid(String plainPassword,
        UserSecurityPolicy policy) {
        boolean shouldContainUppercase = policy.isPasswordMustContainUppercase();
        boolean isValid = !shouldContainUppercase || hasMatch(plainPassword, ONE_MULTIPLE_UPPERCASE_REGEX);
        return createErrorCodeIfInvalid(isValid, SecurityErrorCode.PASSWORD_MUST_CONTAIN_UPPERCASE);
    }

    private static ErrorCode assertLowercasePolicyValid(String plainPassword,
        UserSecurityPolicy policy) {
        boolean shouldContainLowercase = policy.isPasswordMustContainLowercase();
        boolean isValid = !shouldContainLowercase || hasMatch(plainPassword, ONE_MULTIPLE_LOWERCASE_REGEX);
        return createErrorCodeIfInvalid(isValid, SecurityErrorCode.PASSWORD_MUST_CONTAIN_LOWERCASE);
    }

    private static ErrorCode assertNumberPolicyValid(String plainPassword,
        UserSecurityPolicy policy) {
        boolean shouldContainNumber = policy.isPasswordMustContainNumber();
        boolean isValid = !shouldContainNumber || hasMatch(plainPassword, ONE_MULTIPLE_NUMBER_REGEX);
        return createErrorCodeIfInvalid(isValid, SecurityErrorCode.PASSWORD_MUST_CONTAIN_NUMERIC);
    }

    private static ErrorCode assertSpecialCharPolicyValid(String plainPassword,
        UserSecurityPolicy policy) {
        boolean shouldContainSpecialChar = policy.isPasswordMustContainSpecialChar();
        if (shouldContainSpecialChar) {
            boolean isValid = hasMatch(plainPassword, ONE_MULTIPLE_NON_ALPHANUMERIC_REGEX);
            return createErrorCodeIfInvalid(isValid, SecurityErrorCode.PASSWORD_MUST_CONTAIN_SPECIAL_CHAR);
        } else {
            boolean isValid = hasMatch(plainPassword, ONLY_ALPHANUMERIC_REGEX);
            return createErrorCodeIfInvalid(isValid, SecurityErrorCode.PASSWORD_MUST_BE_ALPHANUMERIC);
        }
    }

    private static ErrorCode assertOnlyAlphanumeric(String plainPassword) {
        boolean isValid = plainPassword.matches(ONLY_ALPHANUMERIC_REGEX);
        return createErrorCodeIfInvalid(isValid, SecurityErrorCode.PASSWORD_MUST_BE_ALPHANUMERIC);
    }

    private static boolean hasMatch(String stringToSearch, String regex) {
        Pattern p = Pattern.compile(regex);
        return p.matcher(stringToSearch).find();
    }

    private static ErrorCode createErrorCodeIfInvalid(boolean isValid, ErrorCode errorCode) {
        return !isValid ? errorCode : null;
    }
}
