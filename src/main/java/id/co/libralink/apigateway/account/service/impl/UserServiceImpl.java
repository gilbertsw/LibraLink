package id.co.libralink.apigateway.account.service.impl;

import id.co.libralink.apigateway.account.mapper.UserDetailMapper;
import id.co.libralink.apigateway.account.mapper.UserListMapper;
import id.co.libralink.apigateway.account.mapper.UserRequestMapper;
import id.co.libralink.apigateway.account.model.request.SearchUserRequest;
import id.co.libralink.apigateway.account.model.request.UserRequest;
import id.co.libralink.apigateway.account.model.response.UserDetailResponse;
import id.co.libralink.apigateway.account.model.response.UserListResponse;
import id.co.libralink.apigateway.account.query.UserQuery;
import id.co.libralink.common.constant.CommonConstant;
import id.co.libralink.common.query.SearchQuery;
import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.model.enums.UserStatus;
import id.co.libralink.apigateway.account.repository.UserRepository;
import id.co.libralink.apigateway.account.service.UserService;
import id.co.libralink.common.util.PaginationUtil;
import id.co.libralink.common.util.PreconditionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserListMapper listMapper = new UserListMapper();

    private final UserDetailMapper detailMapper = new UserDetailMapper();

    private final UserRequestMapper requestMapper = new UserRequestMapper();


    @Override
    public Page<UserListResponse> getAllUsers(SearchUserRequest request) {
        UserQuery query = new UserQuery()
                .nameContains(request.getName())
                .emailContains(request.getEmail())
                .roleIn(request.getRoles())
                .statusesIn(request.getStatuses());
        Page<User> page = findAll(query, request.getPageable());
        return PaginationUtil.map(page, listMapper::toTarget);
    }

    @Override
    public UserDetailResponse getUserById(Long id) {
        User entity = PreconditionUtil.assertNotNull(findById(id), User.class);
        return detailMapper.toTarget(entity);
    }

    @Override
    public UserDetailResponse updateUser(Long id, UserRequest request) {
        User oldEntity = PreconditionUtil.assertNotNull(findById(id), User.class);
        User newEntity = requestMapper.fromTarget(request);
        return detailMapper.toTarget(update(oldEntity, newEntity));
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findActiveUserByEmail(String email) {
        return userRepository.findByEmailAndStatusIsIn(email, UserStatus.ACTIVE_STATUSES);
    }

    @Override
    public Page<User> findAll(SearchQuery<User> query, Pageable pageable) {
        return userRepository.findAll(query, pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(User oldEntity, User newEntity) {
        PreconditionUtil.assertNotNull(oldEntity, User.class);
        BeanUtils.copyProperties(newEntity, oldEntity, CommonConstant.DEFAULT_IGNORE_PROPERTIES);
        return userRepository.save(oldEntity);
    }
}
