package id.co.libralink.common.enums;

import id.co.libralink.common.base.code.ErrorCode;

public enum CommonErrorCode implements ErrorCode {
    // Common error
    INVALID_ARGUMENT("invalid.argument"),
    UNEXPECTED_ERROR_OCCURRED("unexpected.error.occurred"),

    // Pagination error
    PARAM_SIZE_INVALID("param.size.invalid"),
    PARAM_PAGE_INVALID("param.page.invalid"),

    // Parameter error
    PARAMETER_EMPTY("error.param.empty"),
    PARAMETER_INVALID("error.param.invalid"),

    // Data related error
    DATA_NOT_FOUND("data.not.found"),
    DATA_NOT_FOUND_FOR_ID("data.not.found.id"),
    DATA_NOT_FOUND_FOR_PARAM("data.not.found.param"),
    DATA_ALREADY_EXIST("data.already.exist"),
    DATA_IS_USED("data.is.used"),
    DATA_REFERENCE_EXCEPTION("data.reference.exception"),

    NOT_IMPLEMENTED("not.implemented"),
    ;

    private final String key;

    CommonErrorCode(String key) {
        this.key = key;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
