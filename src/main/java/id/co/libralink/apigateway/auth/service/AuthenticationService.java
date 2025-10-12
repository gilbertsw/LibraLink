package id.co.libralink.apigateway.auth.service;

import id.co.libralink.apigateway.auth.model.entity.CustomUserDetails;
import id.co.libralink.apigateway.auth.model.request.LoginRequest;
import id.co.libralink.apigateway.auth.model.request.RegisterRequest;
import id.co.libralink.apigateway.auth.model.response.LoginResponse;

public interface AuthenticationService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    CustomUserDetails getLoggedInUser();

}
