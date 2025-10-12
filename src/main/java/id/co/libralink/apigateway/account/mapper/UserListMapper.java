package id.co.libralink.apigateway.account.mapper;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.model.response.UserListResponse;
import id.co.libralink.common.base.mapper.ToTargetMapper;

public class UserListMapper implements ToTargetMapper<User, UserListResponse> {

    @Override
    public UserListResponse toTarget(User source, Object... args) {
        return UserListResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .email(source.getEmail())
                .role(source.getRole())
                .status(source.getStatus())
                .build();
    }

}
