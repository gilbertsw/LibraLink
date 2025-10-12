package id.co.libralink.apigateway.security.service;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.auth.model.request.ChangePasswordRequest;
import id.co.libralink.apigateway.auth.model.request.ResetPasswordRequest;

public interface PasswordValidatorService {

    void validatePassword(String plainPassword);

    void validatePassword(User user, ChangePasswordRequest request);

    void validatePassword(User user, ResetPasswordRequest request);

}
