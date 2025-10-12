package id.co.libralink.apigateway.auth.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {

    @NotEmpty(message = "error.param.empty")
    private String token;

    @NotEmpty(message = "error.param.empty")
    private String newPassword;

}
