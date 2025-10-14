package id.co.libralink.apigateway.security.controller;

import id.co.libralink.apigateway.security.model.vo.UserSecurityPolicyVO;
import id.co.libralink.apigateway.security.service.UserSecurityPolicyService;
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
@RequestMapping("${endpoint.url.v1}${endpoint.admin.user-security-policy}")
@Tag(name = "User Security Policy", description = "Admin User Security Policy APIs")
public class UserSecurityPolicyController extends BaseController {

    private final UserSecurityPolicyService userSecurityPolicyService;

    @Operation(summary = "Get User Security Policy")
    @GetMapping
    public DefaultResponse<UserSecurityPolicyVO> getUserSecurityPolicy() {
        log.info("[RequestId : {}] Execute UserSecurityPolicyController.getUserSecurityPolicy()", MDC.get(MDCKey.REQUEST_ID.name()));
        UserSecurityPolicyVO resp =  userSecurityPolicyService.getPolicyVO();
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Update User Security Policy")
    @PutMapping
    public DefaultResponse<UserSecurityPolicyVO> updateUserSecurityPolicy(@Validated @RequestBody UserSecurityPolicyVO policyVO) {
        log.info("[RequestId : {}] Execute UserSecurityPolicyController.updateUserSecurityPolicy()", MDC.get(MDCKey.REQUEST_ID.name()));
        UserSecurityPolicyVO resp =  userSecurityPolicyService.update(policyVO);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

}
