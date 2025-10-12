package id.co.libralink.common.exception;

import id.co.libralink.common.base.code.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private ErrorCode errorCode;
    private Object[] args;

    public BaseException(ErrorCode errorCode){
        super();
        this.errorCode = errorCode;
        this.args = new Object[]{};
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(ErrorCode errorCode, Object[] args){
        super();
        this.errorCode = errorCode;
        this.args = args;
    }

}
