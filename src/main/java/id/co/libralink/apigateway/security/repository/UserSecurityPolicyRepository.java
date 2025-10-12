package id.co.libralink.apigateway.security.repository;

import id.co.libralink.apigateway.security.model.entity.UserSecurityPolicy;
import id.co.libralink.common.base.repository.BaseRepository;

import java.util.Optional;

public interface UserSecurityPolicyRepository extends BaseRepository<UserSecurityPolicy, Long> {

    Optional<UserSecurityPolicy> findByUserRole(String userRole);

}
