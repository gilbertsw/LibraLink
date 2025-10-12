package id.co.libralink.book.query;

import id.co.libralink.book.enums.BookStatus;
import id.co.libralink.book.model.entity.Book;
import id.co.libralink.common.base.model.entity.BaseEntity;
import id.co.libralink.common.enums.OperationType;
import id.co.libralink.common.query.SearchCriteria;
import id.co.libralink.common.query.SearchQuery;
import id.co.libralink.common.util.StringUtil;
import jakarta.persistence.criteria.*;

import java.util.Date;
import java.util.Set;

public class BookQuery extends SearchQuery<Book> {

    public BookQuery() {
        super();
    }

    public BookQuery titleContains(String title) {
        if (StringUtil.isNotBlank(title)) {
            addCriteria(new SearchCriteria(Book.Fields.title, title, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public BookQuery authorContains(String author) {
        if (StringUtil.isNotBlank(author)) {
            addCriteria(new SearchCriteria(Book.Fields.author, author, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public BookQuery publisherContains(String publisher) {
        if (StringUtil.isNotBlank(publisher)) {
            addCriteria(new SearchCriteria(Book.Fields.publisher, publisher, OperationType.CONTAINS_IGNORE_CASE));
        }
        return this;
    }

    public BookQuery isbnEq(String isbn) {
        if (StringUtil.isNotBlank(isbn)) {
            addCriteria(new SearchCriteria(Book.Fields.isbn, isbn, OperationType.EQUAL));
        }
        return this;
    }

    public BookQuery createdDateBetween(Date fromDate, Date toDate) {
        if (fromDate != null && toDate != null) {
            addCriteria(new SearchCriteria(BaseEntity.Fields.createdDate, fromDate, toDate, OperationType.BETWEEN_DATE));
        }
        return this;
    }

    public BookQuery statusesIn(Set<BookStatus> statuses) {
        if (! statuses.isEmpty()) {
            addCriteria(new SearchCriteria(Book.Fields.status, statuses.toArray(), OperationType.IN));
        }
        return this;
    }

}
