package id.co.libralink.core.borrowing.query;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.core.book.model.entity.Book;
import id.co.libralink.core.borrowing.model.entity.Borrowing;
import id.co.libralink.core.borrowing.enums.BorrowStatus;
import id.co.libralink.common.enums.OperationType;
import id.co.libralink.common.query.SearchCriteria;
import id.co.libralink.common.query.SearchQuery;
import id.co.libralink.common.util.StringUtil;
import jakarta.persistence.criteria.*;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BorrowingQuery extends SearchQuery<Borrowing> {

    @Getter(AccessLevel.PROTECTED)
    private final List<SearchCriteria> userCriterion;

    @Getter(AccessLevel.PROTECTED)
    private final List<SearchCriteria> bookCriterion;

    public BorrowingQuery() {
        super();
        this.userCriterion = new ArrayList<>();
        this.bookCriterion = new ArrayList<>();
    }

    protected void addUserCriteria(SearchCriteria searchCriteria) {
        if (searchCriteria != null) {
            this.userCriterion.add(searchCriteria);
        }
    }

    protected void addBookCriteria(SearchCriteria searchCriteria) {
        if (searchCriteria != null) {
            this.bookCriterion.add(searchCriteria);
        }
    }

    public BorrowingQuery userIdEquals(Long userId) {
        if (userId != null) {
            addUserCriteria(new SearchCriteria("id", userId, OperationType.EQUAL));
        }
        return this;
    }

    public BorrowingQuery userNameContains(String userName) {
        if (StringUtil.isNotBlank(userName)) {
            addUserCriteria(new SearchCriteria("user.name", userName, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public BorrowingQuery userEmailContains(String email) {
        if (StringUtil.isNotBlank(email)) {
            addUserCriteria(new SearchCriteria("user.email", email, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public BorrowingQuery bookTitleContains(String title) {
        if (StringUtil.isNotBlank(title)) {
            addBookCriteria(new SearchCriteria("book.title", title, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public BorrowingQuery bookAuthorContains(String author) {
        if (StringUtil.isNotBlank(author)) {
            addBookCriteria(new SearchCriteria("book.author", author, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public BorrowingQuery bookPublisherContains(String publisher) {
        if (StringUtil.isNotBlank(publisher)) {
            addBookCriteria(new SearchCriteria("book.publisher", publisher, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public BorrowingQuery dueDateBetween(Date fromDate, Date toDate) {
        if (fromDate != null && toDate != null) {
            addCriteria(new SearchCriteria(Borrowing.Fields.dueDate, fromDate, toDate, OperationType.BETWEEN_DATE));
        }
        return this;
    }

    public BorrowingQuery borrowDateBetween(Date fromDate, Date toDate) {
        if (fromDate != null && toDate != null) {
            addCriteria(new SearchCriteria(Borrowing.Fields.borrowDate, fromDate, toDate, OperationType.BETWEEN_DATE));
        }
        return this;
    }

    public BorrowingQuery statusesIn(Set<BorrowStatus> statuses) {
        if (! statuses.isEmpty()) {
            addCriteria(new SearchCriteria(Borrowing.Fields.status, statuses.toArray(), OperationType.IN));
        }
        return this;
    }

    @Override
    public Predicate toPredicate(Root<Borrowing> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        Join<Borrowing, User> userJoin = root.join(Borrowing.Fields.user, JoinType.INNER);
        Join<Borrowing, Book> bookJoin = root.join(Borrowing.Fields.book, JoinType.INNER);

        for (SearchCriteria joinCriteria : this.getUserCriterion()) {
            predicates.add(this.buildPredicate(joinCriteria, userJoin, builder));
        }

        for (SearchCriteria joinCriteria : this.getBookCriterion()) {
            predicates.add(this.buildPredicate(joinCriteria, bookJoin, builder));
        }

        for (SearchCriteria criteria : this.getCriterion()) {
            predicates.add(this.buildPredicate(criteria, root, builder));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
