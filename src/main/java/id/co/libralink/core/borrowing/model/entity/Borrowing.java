package id.co.libralink.core.borrowing.model.entity;

import id.co.libralink.core.book.model.entity.Book;
import id.co.libralink.core.borrowing.enums.BorrowStatus;
import id.co.libralink.common.base.model.entity.BaseEntity;
import id.co.libralink.apigateway.account.model.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "borrowings")
public class Borrowing extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BorrowStatus status;

    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "return_date")
    private Date returnDate;

}
