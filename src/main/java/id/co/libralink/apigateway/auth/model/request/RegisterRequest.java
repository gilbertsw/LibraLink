package id.co.libralink.apigateway.auth.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "error.param.empty")
    private String name;

    @NotBlank(message = "error.param.empty")
    @Email(message = "error.email.invalid")
    private String email;

    @NotBlank(message = "error.param.empty")
    private String password;

}
