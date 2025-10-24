package id.co.libralink.core.dashboard.controller;

import id.co.libralink.common.base.controller.BaseController;
import id.co.libralink.common.enums.CommonSuccessCode;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.web.model.DefaultResponse;
import id.co.libralink.core.dashboard.model.request.DashboardRequest;
import id.co.libralink.core.dashboard.model.response.DashboardResponse;
import id.co.libralink.core.dashboard.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.url.v1}${endpoint.admin.dashboard}")
@Tag(name = "Dashboard", description = "Admin Dashboard APIs")
public class AdminDashboardController extends BaseController {

    private final DashboardService dashboardService;

    @Operation(summary = "Get Summary")
    @GetMapping
    public DefaultResponse<DashboardResponse> getSummary(@Valid DashboardRequest request) {
        log.info("[RequestId : {}] Execute AdminDashboardController.getSummary()", MDC.get(MDCKey.REQUEST_ID.name()));
        DashboardResponse resp = dashboardService.getSummary(request);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

}
