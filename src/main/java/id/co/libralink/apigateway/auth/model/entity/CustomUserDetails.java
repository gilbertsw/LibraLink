package id.co.libralink.apigateway.auth.model.entity;

import id.co.libralink.apigateway.auth.constant.AuthConstant;
import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.enums.UserStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(AuthConstant.AUTHORITY_PREFIX + user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getStatus().equals(UserStatus.DISABLED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getStatus().equals(UserStatus.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getStatus().equals(UserStatus.DISABLED);
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().isActive();
    }
}
