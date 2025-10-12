package id.co.libralink.apigateway.security.service.impl;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.auth.model.request.ChangePasswordRequest;
import id.co.libralink.apigateway.auth.model.request.ResetPasswordRequest;
import id.co.libralink.apigateway.security.enums.SecurityErrorCode;
import id.co.libralink.apigateway.security.exception.PasswordException;
import id.co.libralink.apigateway.security.model.entity.UserSecurityPolicy;
import id.co.libralink.apigateway.security.service.PasswordValidatorService;
import id.co.libralink.apigateway.security.service.UserSecurityPolicyService;
import id.co.libralink.apigateway.security.util.PasswordFormatUtil;
import id.co.libralink.common.base.code.ErrorCode;
import id.co.libralink.common.util.PreconditionUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PasswordValidatorServiceImpl implements PasswordValidatorService {

    private final UserSecurityPolicyService userSecurityPolicyService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void validatePassword(String plainPassword) {
        UserSecurityPolicy policy = userSecurityPolicyService.getPolicy();
        validateAgainstPolicy(plainPassword,  policy);
    }

    @Override
    public void validatePassword(User user, ChangePasswordRequest request) {
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();

        PreconditionUtil.assertTrue(isOldPasswordMatch(user, oldPassword),
                SecurityErrorCode.INVALID_OLD_PASSWORD, "invalid old password");

        UserSecurityPolicy policy = userSecurityPolicyService.getPolicy();
        validateAgainstPolicy(user, newPassword, policy);
    }

    @Override
    public void validatePassword(User user, ResetPasswordRequest request) {
        String newPassword = request.getNewPassword();
        UserSecurityPolicy policy = userSecurityPolicyService.getPolicy();
        validateAgainstPolicy(user, newPassword, policy);
    }

    private ErrorCode assertNewPasswordNotSameWithOldPassword(User user, String newPassword) {
        if (isOldPasswordMatch(user, newPassword)) {
            return SecurityErrorCode.NEW_PASSWORD_SAME_WITH_OLD_PASSWORD;
        }
        return null;
    }

    private ErrorCode assertUsernamePolicyValid(String plainPassword, String username,
                                                UserSecurityPolicy policy) {
        if (policy.isPreventUsernameAsPassword() && plainPassword.toLowerCase().contains(username.toLowerCase())) {
            return SecurityErrorCode.PASSWORD_CANNOT_CONTAIN_USERNAME;
        }
        return null;
    }

    private boolean isOldPasswordMatch(User user, String oldPassword) {
        return StringUtils.isNotBlank(user.getPassword()) && bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
    }

    private void validateAgainstPolicy(String rawPassword, UserSecurityPolicy policy) {
        List<ErrorCode> errors = new ArrayList<>(PasswordFormatUtil.validateFormat(rawPassword, policy));

        errors = errors.stream().filter(Objects::nonNull).collect(Collectors.toList());

        if (!errors.isEmpty()) {
            throw new PasswordException("Invalid password", errors);
        }
    }

    private void validateAgainstPolicy(User user, String rawPassword, UserSecurityPolicy policy) {
        List<ErrorCode> errors = new ArrayList<>();
        errors.add(assertNewPasswordNotSameWithOldPassword(user, rawPassword));
        errors.add(assertUsernamePolicyValid(rawPassword, user.getEmail(), policy));
        errors.addAll(PasswordFormatUtil.validateFormat(rawPassword, policy));

        errors = errors.stream().filter(Objects::nonNull).collect(Collectors.toList());

        if (!errors.isEmpty()) {
            throw new PasswordException("Invalid password", errors);
        }
    }

}
