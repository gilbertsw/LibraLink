package id.co.libralink.apigateway.security.service.impl;

import id.co.libralink.apigateway.security.model.entity.UserSecurityPolicy;
import id.co.libralink.apigateway.security.repository.UserSecurityPolicyRepository;
import id.co.libralink.apigateway.security.service.UserSecurityPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityPolicyServiceImpl implements UserSecurityPolicyService {

    private final UserSecurityPolicyRepository repository;

    @Override
    public UserSecurityPolicy getPolicy() {
        return repository.findAll().getFirst();
    }

}
