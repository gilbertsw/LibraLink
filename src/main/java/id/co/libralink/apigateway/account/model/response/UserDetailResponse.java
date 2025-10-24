package id.co.libralink.apigateway.account.model.response;

import id.co.libralink.apigateway.account.enums.UserRole;
import id.co.libralink.apigateway.account.enums.UserStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {

    private Long id;

    private String name;

    private String email;

    private UserRole role;

    private UserStatus status;

}
