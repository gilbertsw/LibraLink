package id.co.libralink.core.borrowing.model.response;

import id.co.libralink.core.borrowing.enums.BorrowStatus;
import lombok.*;

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
