package id.co.libralink.apigateway.auth.controller;

import id.co.libralink.apigateway.auth.model.request.LoginRequest;
import id.co.libralink.apigateway.auth.model.request.RegisterRequest;
import id.co.libralink.apigateway.auth.model.response.LoginResponse;
import id.co.libralink.apigateway.auth.service.AuthenticationService;
import id.co.libralink.common.base.controller.BaseController;
import id.co.libralink.common.enums.CommonSuccessCode;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.web.model.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.url.v1}${endpoint.auth.base}")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthenticationController extends BaseController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register New User")
    @PostMapping("${endpoint.auth.register}")
    public DefaultResponse<Void> register(@Valid @RequestBody RegisterRequest req) {
        log.info("[RequestId : {}] Execute AuthenticationController.register()", MDC.get(MDCKey.REQUEST_ID.name()));
        authenticationService.register(req);
        return responseService.build(HttpStatus.CREATED, CommonSuccessCode.SUCCESS);
    }

    @Operation(summary = "Login")
    @PostMapping("${endpoint.auth.login}")
    public DefaultResponse<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        log.info("[RequestId : {}] Execute AuthenticationController.login()", MDC.get(MDCKey.REQUEST_ID.name()));
        LoginResponse resp = authenticationService.login(req);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

}
