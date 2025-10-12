package id.co.libralink.apigateway.account.model.request;

import id.co.libralink.apigateway.account.model.enums.UserRole;
import id.co.libralink.apigateway.account.model.enums.UserStatus;
import id.co.libralink.common.model.request.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserRequest extends PaginationRequest {

    private String name;

    private String email;

    private Set<UserRole> roles = new HashSet<>();

    private Set<UserStatus> statuses = new HashSet<>();

}
