package id.co.libralink.common.exception;

import id.co.libralink.common.enums.CommonErrorCode;
import lombok.Getter;

@Getter
public class DataNotFoundException extends BaseException {

    private Class<?> clazz;

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(Class<?> clazz) {
        super(CommonErrorCode.DATA_NOT_FOUND, new Object[]{clazz.getSimpleName()});
        this.clazz = clazz;
    }

    public DataNotFoundException(Class<?> clazz, Long id) {
        super(CommonErrorCode.DATA_NOT_FOUND_FOR_ID, new Object[]{clazz.getSimpleName(), id});
        this.clazz = clazz;
    }

    public DataNotFoundException(Class<?> clazz, String param) {
        super(CommonErrorCode.DATA_NOT_FOUND_FOR_PARAM, new Object[]{clazz.getSimpleName(), param});
        this.clazz = clazz;
    }

}
