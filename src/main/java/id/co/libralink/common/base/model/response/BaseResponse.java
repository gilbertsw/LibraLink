package id.co.libralink.common.base.model.response;

import id.co.libralink.common.base.code.BaseCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public abstract class BaseResponse {

    protected Integer status;
    protected String code;
    protected String message;
    protected LocalDateTime timestamp;

    public BaseResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public BaseResponse(HttpStatus status) {
        this();
        this.status = status.value();
        this.code = status.name();
        this.message = status.getReasonPhrase();
    }

    public BaseResponse(HttpStatus status, String message) {
        this();
        this.status = status.value();
        this.code = status.name();
        this.message = message;
    }

    public BaseResponse(HttpStatus status, BaseCode code, String message) {
        this();
        this.status = status.value();
        this.code = code.getCode();
        this.message = message;
    }

}
