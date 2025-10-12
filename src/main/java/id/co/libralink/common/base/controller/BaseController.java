package id.co.libralink.common.base.controller;

import id.co.libralink.apigateway.auth.model.entity.CustomUserDetails;
import id.co.libralink.apigateway.auth.service.AuthenticationService;
import id.co.libralink.common.web.service.DefaultResponseService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    protected DefaultResponseService responseService;

    @Autowired
    private AuthenticationService authenticationService;


    protected CustomUserDetails getUserDetail() {
        return authenticationService.getLoggedInUser();
    }

}
