package id.co.libralink.apigateway.account.repository;

import id.co.libralink.common.base.repository.BaseRepository;
import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.model.enums.UserStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndStatusIsIn(String email, Set<UserStatus> status);

}
