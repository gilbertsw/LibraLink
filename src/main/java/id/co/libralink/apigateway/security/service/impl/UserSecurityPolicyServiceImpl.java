package id.co.libralink.apigateway.security.service.impl;

import id.co.libralink.apigateway.security.mapper.UserSecurityPolicyMapper;
import id.co.libralink.apigateway.security.model.entity.UserSecurityPolicy;
import id.co.libralink.apigateway.security.model.vo.UserSecurityPolicyVO;
import id.co.libralink.apigateway.security.repository.UserSecurityPolicyRepository;
import id.co.libralink.apigateway.security.service.UserSecurityPolicyService;
import id.co.libralink.common.constant.CommonConstant;
import id.co.libralink.common.util.PreconditionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityPolicyServiceImpl implements UserSecurityPolicyService {

    private final UserSecurityPolicyRepository repository;

    private final UserSecurityPolicyMapper mapper = new UserSecurityPolicyMapper();


    @Override
    public UserSecurityPolicy getPolicy() {
        return repository.findAll().getFirst();
    }

    @Override
    public UserSecurityPolicyVO getPolicyVO() {
        UserSecurityPolicy policy = repository.findAll().getFirst();
        return mapper.toTarget(policy);
    }

    @Override
    public UserSecurityPolicyVO update(UserSecurityPolicyVO policyVO) {
        UserSecurityPolicy newEntity = mapper.fromTarget(policyVO);
        UserSecurityPolicy oldEntity = repository.findAll().getFirst();
        PreconditionUtil.assertNotNull(oldEntity, UserSecurityPolicy.class);
        BeanUtils.copyProperties(newEntity, oldEntity, CommonConstant.DEFAULT_IGNORE_PROPERTIES);
        return mapper.toTarget(repository.save(oldEntity));
    }

}
