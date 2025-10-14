package id.co.libralink.apigateway.security.model.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurityPolicyVO {

    private Integer passwordMinLength;

    private Integer maxFailedLoginAttempt;

    private Integer minPasswordReuseInterval;

    private Boolean preventUsernameAsPassword;

    private Boolean passwordMustContainUppercase;

    private Boolean passwordMustContainLowercase;

    private Boolean passwordMustContainNumber;

    private Boolean passwordMustContainSpecialChar;

}
