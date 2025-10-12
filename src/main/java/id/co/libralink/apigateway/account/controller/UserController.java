package id.co.libralink.apigateway.account.controller;

import id.co.libralink.apigateway.account.mapper.UserDetailMapper;
import id.co.libralink.apigateway.account.model.request.UserRequest;
import id.co.libralink.apigateway.account.model.response.UserDetailResponse;
import id.co.libralink.apigateway.account.service.UserService;
import id.co.libralink.apigateway.auth.model.entity.CustomUserDetails;
import id.co.libralink.common.base.controller.BaseController;
import id.co.libralink.common.enums.CommonSuccessCode;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.web.model.DefaultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.url.v1}${endpoint.user.users}")
@Tag(name = "User", description = "User APIs")
public class UserController extends BaseController {

    private final UserService userService;

    private final UserDetailMapper detailMapper = new UserDetailMapper();

    @Operation(summary = "Get Current User Detail")
    @GetMapping("${endpoint.users.me}")
    public DefaultResponse<UserDetailResponse> getCurrentUser() {
        log.info("[RequestId : {}] Execute UserController.getCurrentUser()", MDC.get(MDCKey.REQUEST_ID.name()));
        CustomUserDetails userDetails = getUserDetail();
        UserDetailResponse resp = detailMapper.toTarget(userDetails.getUser());
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Update User Detail by ID")
    @PutMapping("${endpoint.users.id}")
    public DefaultResponse<UserDetailResponse> updateUser(@PathVariable("id") Long id,
                                                          @Validated @RequestBody UserRequest request) {
        log.info("[RequestId : {}] Execute UserController.updateUser()", MDC.get(MDCKey.REQUEST_ID.name()));
        UserDetailResponse resp =  userService.updateUser(id, request);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

}
