package id.co.libralink.core.book.model.response;

import id.co.libralink.core.book.enums.BookStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookListResponse {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private BookStatus status;

}
