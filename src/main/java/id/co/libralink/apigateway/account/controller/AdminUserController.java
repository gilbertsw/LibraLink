package id.co.libralink.apigateway.account.controller;

import id.co.libralink.apigateway.account.model.request.SearchUserRequest;
import id.co.libralink.apigateway.account.model.response.UserDetailResponse;
import id.co.libralink.apigateway.account.model.response.UserListResponse;
import id.co.libralink.apigateway.account.service.UserService;
import id.co.libralink.common.base.controller.BaseController;
import id.co.libralink.common.enums.CommonSuccessCode;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.web.model.DefaultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.url.v1}${endpoint.admin.users}")
@Tag(name = "User", description = "Admin User APIs")
public class AdminUserController extends BaseController {

    private final UserService userService;

    @Operation(summary = "Get All and Search Users")
    @GetMapping
    public DefaultResponse<Page<UserListResponse>> getAllUsers(@Valid SearchUserRequest request) {
        log.info("[RequestId : {}] Execute AdminUserController.getAllUsers()", MDC.get(MDCKey.REQUEST_ID.name()));
        Page<UserListResponse> resp = userService.getAllUsers(request);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Get User Detail by ID")
    @GetMapping("${endpoint.users.id}")
    public DefaultResponse<UserDetailResponse> getUserById(@PathVariable("id") Long id) {
        log.info("[RequestId : {}] Execute AdminUserController.getUserById()", MDC.get(MDCKey.REQUEST_ID.name()));
        UserDetailResponse resp =  userService.getUserById(id);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }


}
