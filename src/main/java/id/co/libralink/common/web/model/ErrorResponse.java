package id.co.libralink.common.web.model;

import id.co.libralink.common.base.code.BaseCode;
import id.co.libralink.common.base.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ErrorResponse extends BaseResponse {

    private List<FieldErrorMessage> errors;

    public ErrorResponse(HttpStatus status, String message) {
        super(status, message);
    }

    public ErrorResponse(HttpStatus status, BaseCode code, String message) {
        super(status, code, message);
    }

    public ErrorResponse(HttpStatus status, BaseCode code, String message, List<FieldErrorMessage> errors) {
        super(status, code, message);
        this.errors = errors;
    }

}
