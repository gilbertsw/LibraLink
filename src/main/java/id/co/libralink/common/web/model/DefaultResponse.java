package id.co.libralink.common.web.model;

import id.co.libralink.common.base.code.BaseCode;
import id.co.libralink.common.base.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DefaultResponse<T> extends BaseResponse {

    private T data;

    public DefaultResponse(HttpStatus status, T data) {
        super(status);
        this.data = data;
    }

    public DefaultResponse(HttpStatus status, String message, T data) {
        super(status, message);
        this.data = data;
    }

    public DefaultResponse(HttpStatus status, BaseCode code, String message) {
        super(status, code, message);
    }

    public DefaultResponse(HttpStatus status, BaseCode code, String message, T data) {
        super(status, code, message);
        this.data = data;
    }

}
