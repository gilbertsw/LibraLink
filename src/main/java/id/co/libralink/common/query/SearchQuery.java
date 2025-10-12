package id.co.libralink.common.query;

import jakarta.persistence.criteria.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class SearchQuery<T> implements Specification<T> {

    private final List<SearchCriteria> criterion;

    protected SearchQuery() {
        this.criterion = new ArrayList<>();
    }

    protected void addCriteria(SearchCriteria searchCriteria) {
        if (searchCriteria != null) {
            this.criterion.add(searchCriteria);
        }
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.and(this.criterion.stream()
                .map(criteria -> buildPredicate(criteria, root, builder))
                .toArray(Predicate[]::new)
        );
    }

    protected Predicate buildPredicate(SearchCriteria criteria, From<T, ?> root, CriteriaBuilder builder) {
        return switch (criteria.getOperationType()) {
            case EQUAL -> builder.equal(root.get(criteria.getFieldName()), criteria.getValue());
            case NOT_EQUAL -> builder.notEqual(root.get(criteria.getFieldName()), criteria.getValue());
            case CONTAINS -> builder.like(root.get(criteria.getFieldName()), "%" + criteria.getValue().toString() + "%");
            case START_WITH -> builder.like(root.get(criteria.getFieldName()), criteria.getValue().toString() + "%");
            case END_WITH -> builder.like(root.get(criteria.getFieldName()), "%" + criteria.getValue().toString());
            case IN -> root.get(criteria.getFieldName()).in((Object[]) criteria.getValue());
            case NOT_IN -> builder.not(root.get(criteria.getFieldName()).in((Object[]) criteria.getValue()));
            case GREATER_THAN -> builder.greaterThan(root.get(criteria.getFieldName()), criteria.getValue().toString());
            case GREATER_THAN_EQUAL -> builder.greaterThanOrEqualTo(root.get(criteria.getFieldName()), criteria.getValue().toString());
            case LESS_THAN -> builder.lessThan(root.get(criteria.getFieldName()), criteria.getValue().toString());
            case LESS_THAN_EQUAL -> builder.lessThanOrEqualTo(root.get(criteria.getFieldName()), criteria.getValue().toString());
            case IS_NULL -> builder.isNull(root.get(criteria.getFieldName()));
            case IS_NOT_NULL -> builder.isNotNull(root.get(criteria.getFieldName()));
            case BETWEEN_DATE -> builder.between(root.get(criteria.getFieldName()), (Date) criteria.getValue(), (Date) criteria.getValueB());
            case CONTAINS_IGNORE_CASE -> builder.like(
                    builder.lower(root.get(criteria.getFieldName())),
                    "%" + criteria.getValue().toString().toLowerCase() + "%"
            );
            case LIKE_IGNORE_CASE -> builder.like(
                    builder.lower(root.get(criteria.getFieldName())),
                    criteria.getValue().toString().toLowerCase()
            );
            case LIKE -> builder.like(
                    root.get(criteria.getFieldName()),
                    criteria.getValue().toString()
            );

        };
    }

}
