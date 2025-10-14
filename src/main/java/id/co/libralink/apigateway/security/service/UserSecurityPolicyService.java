package id.co.libralink.apigateway.security.service;

import id.co.libralink.apigateway.security.model.entity.UserSecurityPolicy;
import id.co.libralink.apigateway.security.model.vo.UserSecurityPolicyVO;

public interface UserSecurityPolicyService {

    UserSecurityPolicy getPolicy();

    UserSecurityPolicyVO getPolicyVO();

    UserSecurityPolicyVO update(UserSecurityPolicyVO policy);

}
