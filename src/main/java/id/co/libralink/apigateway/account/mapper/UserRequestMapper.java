package id.co.libralink.apigateway.account.mapper;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.model.request.UserRequest;
import id.co.libralink.book.model.entity.Book;
import id.co.libralink.book.model.request.BookRequest;
import id.co.libralink.common.base.mapper.FromTargetMapper;

public class UserRequestMapper implements FromTargetMapper<User, UserRequest> {

    @Override
    public User fromTarget(UserRequest target, Object... args) {
        return User.builder()
                .name(target.getName())
                .email(target.getEmail())
                .status(target.getStatus())
                .build();
    }
}
