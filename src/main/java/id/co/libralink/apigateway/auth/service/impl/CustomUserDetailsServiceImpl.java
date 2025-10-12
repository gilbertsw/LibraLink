package id.co.libralink.apigateway.auth.service.impl;

import id.co.libralink.apigateway.auth.model.entity.CustomUserDetails;
import id.co.libralink.apigateway.auth.service.CustomUserDetailsService;
import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new CustomUserDetails(user);
    }

}
