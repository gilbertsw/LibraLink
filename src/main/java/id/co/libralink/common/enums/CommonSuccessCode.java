package id.co.libralink.common.enums;

import id.co.libralink.common.base.code.SuccessCode;

public enum CommonSuccessCode implements SuccessCode {
    SUCCESS("success"),
    ;

    private final String key;

    CommonSuccessCode(String key) {
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
