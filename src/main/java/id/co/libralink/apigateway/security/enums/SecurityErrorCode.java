package id.co.libralink.apigateway.security.enums;

import id.co.libralink.common.base.code.ErrorCode;

public enum SecurityErrorCode implements ErrorCode {
    INVALID_OLD_PASSWORD("invalid.old.password"),
    INVALID_USERNAME_OR_PASSWORD("invalid.username.or.password"),
    SINGLE_DEVICE_SIGN_ON("single.device.sign.on"),
    USER_IS_LOCKED("user.is.locked"),
    USER_NOT_ACTIVE("user.not.active"),
    USER_NOT_AUTHENTICATED("user.not.authenticated"),
    USERNAME_NOT_FOUND("username.not.found"),
    USERNAME_ALREADY_USED("username.already.used"),
    ACCESS_NOT_MATCH("access.not.match"),
    TOKEN_INVALID("token.invalid"),
    TOKEN_ALREADY_EXPIRED("token.already.expired"),
    TOKEN_ALREADY_INVALIDATED("token.already.invalidated"),
    PASSWORD_CANNOT_BE_REUSED("password.cannot.be.reused"),
    NEW_PASSWORD_SAME_WITH_OLD_PASSWORD("new.password.same.with.old.password"),
    PASSWORD_CANNOT_CONTAIN_USERNAME("password.cannot.contain.username"),
    PASSWORD_TOO_SHORT("password.too.short"),
    PASSWORD_MUST_CONTAIN_UPPERCASE("password.must.contain.uppercase"),
    PASSWORD_MUST_CONTAIN_LOWERCASE("password.must.contain.lowercase"),
    PASSWORD_MUST_CONTAIN_NUMERIC("password.must.contain.numeric"),
    PASSWORD_MUST_CONTAIN_SPECIAL_CHAR("password.must.contain.special.char"),
    PASSWORD_MUST_BE_ALPHANUMERIC("password.must.be.alphanumeric"),
    ;

    private static final String PREFIX = "security.";

    private final String key;

    SecurityErrorCode(String key) {
        this.key = key;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getKey() {
        return PREFIX + this.key;
    }
}
