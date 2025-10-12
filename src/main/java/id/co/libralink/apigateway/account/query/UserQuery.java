package id.co.libralink.apigateway.account.query;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.apigateway.account.model.enums.UserRole;
import id.co.libralink.apigateway.account.model.enums.UserStatus;
import id.co.libralink.common.base.model.entity.BaseEntity;
import id.co.libralink.common.enums.OperationType;
import id.co.libralink.common.query.SearchCriteria;
import id.co.libralink.common.query.SearchQuery;
import id.co.libralink.common.util.StringUtil;

import java.util.Set;

public class UserQuery extends SearchQuery<User> {

    public UserQuery() {
        super();
    }

    public UserQuery nameContains(String name) {
        if (StringUtil.isNotBlank(name)) {
            addCriteria(new SearchCriteria(User.Fields.name, name, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public UserQuery emailContains(String email) {
        if (StringUtil.isNotBlank(email)) {
            addCriteria(new SearchCriteria(User.Fields.email, email, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public UserQuery roleIn(Set<UserRole> roles) {
        if (! roles.isEmpty()) {
            addCriteria(new SearchCriteria(User.Fields.role, roles.toArray(), OperationType.IN));
        }
        return this;
    }

    public UserQuery statusesIn(Set<UserStatus> statuses) {
        if (! statuses.isEmpty()) {
            addCriteria(new SearchCriteria(User.Fields.status, statuses.toArray(), OperationType.IN));
        }
        return this;
    }

}
