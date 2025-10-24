package id.co.libralink.core.borrowing.model.response;

import id.co.libralink.core.borrowing.enums.BorrowStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingDetailResponse {

    private Long id;

    private String userName;

    private String userEmail;

    private String bookTitle;

    private String bookAuthor;

    private String bookPublisher;

    private BorrowStatus status;

    private Date borrowDate;

    private Date dueDate;

    private Date returnDate;

}
