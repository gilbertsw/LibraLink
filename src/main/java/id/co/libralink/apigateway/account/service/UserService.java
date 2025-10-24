package id.co.libralink.apigateway.account.service;

import id.co.libralink.apigateway.account.enums.UserRole;
import id.co.libralink.apigateway.account.enums.UserStatus;
import id.co.libralink.apigateway.account.model.request.SearchUserRequest;
import id.co.libralink.apigateway.account.model.request.UserRequest;
import id.co.libralink.apigateway.account.model.response.UserDetailResponse;
import id.co.libralink.apigateway.account.model.response.UserListResponse;
import id.co.libralink.common.base.service.BaseService;
import id.co.libralink.apigateway.account.model.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface UserService extends BaseService<User> {

    Page<UserListResponse> getAllUsers(SearchUserRequest request);

    UserDetailResponse getUserById(Long id);

    UserDetailResponse updateUser(Long id, UserRequest request);

    User save(User entity);

    Optional<User> findByEmail(String email);

    Optional<User> findActiveUserByEmail(String email);

    Integer countByRoleAndStatus(Set<UserRole> roles, Set<UserStatus> statuses);

}
