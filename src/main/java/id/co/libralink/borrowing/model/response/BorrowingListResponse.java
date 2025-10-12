package id.co.libralink.borrowing.model.response;

import id.co.libralink.borrowing.model.enums.BorrowStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingListResponse {

    private Long id;

    private String userName;

    private String userEmail;

    private String bookTitle;

    private String bookAuthor;

    private BorrowStatus status;

}
