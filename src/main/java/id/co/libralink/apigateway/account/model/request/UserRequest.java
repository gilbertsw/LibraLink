package id.co.libralink.apigateway.account.model.request;

import id.co.libralink.apigateway.account.model.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "error.param.empty")
    private String name;

    @NotBlank(message = "error.param.empty")
    private String email;

    @NotNull(message = "error.param.empty")
    private UserStatus status;

}
