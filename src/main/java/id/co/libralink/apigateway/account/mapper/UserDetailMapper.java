package id.co.libralink.apigateway.account.mapper;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.model.response.UserDetailResponse;
import id.co.libralink.common.base.mapper.ToTargetMapper;

public class UserDetailMapper implements ToTargetMapper<User, UserDetailResponse> {

    @Override
    public UserDetailResponse toTarget(User source, Object... args) {
        return UserDetailResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .email(source.getEmail())
                .role(source.getRole())
                .status(source.getStatus())
                .build();
    }

}
