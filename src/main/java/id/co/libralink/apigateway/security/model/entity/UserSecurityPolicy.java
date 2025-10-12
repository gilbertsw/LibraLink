package id.co.libralink.apigateway.security.model.entity;

import id.co.libralink.common.base.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_security_policies")
public class UserSecurityPolicy extends BaseEntity {

    @Column(name = "password_min_length")
    private Integer passwordMinLength;

    @Column(name = "max_failed_login_attempt")
    private Integer maxFailedLoginAttempt;

    @Column(name = "min_password_reuse_interval")
    private Integer minPasswordReuseInterval;

    @Column(name = "prevent_username_as_password")
    private Boolean preventUsernameAsPassword;

    @Column(name = "password_must_contain_uppercase")
    private Boolean passwordMustContainUppercase;

    @Column(name = "password_must_contain_lowercase")
    private Boolean passwordMustContainLowercase;

    @Column(name = "password_must_contain_number")
    private Boolean passwordMustContainNumber;

    @Column(name = "password_must_contain_special_char")
    private Boolean passwordMustContainSpecialChar;


    private boolean isTrue(Boolean bool) {
        return bool != null && bool;
    }

    public boolean isPreventUsernameAsPassword() {
        return isTrue(preventUsernameAsPassword);
    }

    public boolean isPasswordMustContainUppercase() {
        return isTrue(passwordMustContainUppercase);
    }

    public boolean isPasswordMustContainLowercase() {
        return isTrue(passwordMustContainLowercase);
    }

    public boolean isPasswordMustContainNumber() {
        return isTrue(passwordMustContainNumber);
    }

    public boolean isPasswordMustContainSpecialChar() {
        return isTrue(passwordMustContainSpecialChar);
    }

}
