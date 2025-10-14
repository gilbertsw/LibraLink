package id.co.libralink.apigateway.security.mapper;

import id.co.libralink.apigateway.security.model.entity.UserSecurityPolicy;
import id.co.libralink.apigateway.security.model.vo.UserSecurityPolicyVO;
import id.co.libralink.common.base.mapper.BaseMapper;

public class UserSecurityPolicyMapper implements BaseMapper<UserSecurityPolicy, UserSecurityPolicyVO> {

    @Override
    public UserSecurityPolicy fromTarget(UserSecurityPolicyVO target, Object... args) {
        return UserSecurityPolicy.builder()
                .passwordMinLength(target.getPasswordMinLength())
                .maxFailedLoginAttempt(target.getMaxFailedLoginAttempt())
                .minPasswordReuseInterval(target.getMinPasswordReuseInterval())
                .preventUsernameAsPassword(target.getPreventUsernameAsPassword())
                .passwordMustContainUppercase(target.getPasswordMustContainUppercase())
                .passwordMustContainLowercase(target.getPasswordMustContainLowercase())
                .passwordMustContainNumber(target.getPasswordMustContainNumber())
                .passwordMustContainSpecialChar(target.getPasswordMustContainSpecialChar())
                .build();
    }

    @Override
    public UserSecurityPolicyVO toTarget(UserSecurityPolicy source, Object... args) {
        return UserSecurityPolicyVO.builder()
                .passwordMinLength(source.getPasswordMinLength())
                .maxFailedLoginAttempt(source.getMaxFailedLoginAttempt())
                .minPasswordReuseInterval(source.getMinPasswordReuseInterval())
                .preventUsernameAsPassword(source.getPreventUsernameAsPassword())
                .passwordMustContainUppercase(source.getPasswordMustContainUppercase())
                .passwordMustContainLowercase(source.getPasswordMustContainLowercase())
                .passwordMustContainNumber(source.getPasswordMustContainNumber())
                .passwordMustContainSpecialChar(source.getPasswordMustContainSpecialChar())
                .build();
    }
}
