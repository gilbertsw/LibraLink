package id.co.libralink.apigateway.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "error.param.empty")
    private String email;

    @NotBlank(message = "error.param.empty")
    private String password;

}
