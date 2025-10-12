package id.co.libralink.apigateway.auth.service.impl;

import id.co.libralink.apigateway.auth.constant.AuthConstant;
import id.co.libralink.apigateway.auth.model.entity.CustomUserDetails;
import id.co.libralink.apigateway.auth.model.request.LoginRequest;
import id.co.libralink.apigateway.auth.model.request.RegisterRequest;
import id.co.libralink.apigateway.auth.model.response.LoginResponse;
import id.co.libralink.apigateway.auth.service.AuthenticationService;
import id.co.libralink.apigateway.security.enums.SecurityErrorCode;
import id.co.libralink.apigateway.security.service.PasswordValidatorService;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.exception.BaseException;
import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.service.UserService;
import id.co.libralink.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    @Value("${security.jwt.expiry-minutes}")
    private Integer jwtExpiryInMinutes;

    private final JwtEncoder jwtEncoder;

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserService userService;

    private final PasswordValidatorService passwordValidatorService;

    @Override
    @Transactional
    public void register(RegisterRequest req) {
        Optional<User> registeredUser = userService.findActiveUserByEmail(req.getEmail());
        if (registeredUser.isPresent()) {
            throw new BaseException(SecurityErrorCode.USERNAME_ALREADY_USED);
        }

        String password = req.getPassword();
        passwordValidatorService.validatePassword(password);

        String encodedPassword = bCryptPasswordEncoder.encode(password);

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(encodedPassword);
        user.setName(req.getName());

        userService.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(StringUtil.SPACE));

        Instant now = Instant.now();
        Instant expires = now.plus(jwtExpiryInMinutes, ChronoUnit.MINUTES);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(expires)
                .subject(userDetails.getUsername())
                .claim(AuthConstant.AUTHORITY_CLAIMS, authorities)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(token, expires, authorities);
    }

    @Override
    public CustomUserDetails getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertUserIsAuthenticated(authentication);

        if (authentication instanceof JwtAuthenticationToken jwtToken) {
            String username = jwtToken.getToken().getSubject();
            Optional<User> user = userService.findByEmail(username);
            if (user.isEmpty()) {
                throw new BaseException(SecurityErrorCode.TOKEN_INVALID);
            }
            return new CustomUserDetails(user.get());
        }

        return (CustomUserDetails) authentication.getPrincipal();
    }

    private void assertUserIsAuthenticated(Authentication authentication) {
        if (authentication == null || ! authentication.isAuthenticated()) {
            log.warn("[RequestId : {}] Authentication failed: User is not authenticated.", MDC.get(MDCKey.REQUEST_ID.name()));
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated.");
        }
    }

}
