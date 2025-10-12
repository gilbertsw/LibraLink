package id.co.libralink.book.model.response;

import lombok.*;
import id.co.libralink.book.enums.BookStatus;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailResponse {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String isbn;

    private String description;

    private Integer totalStock;

    private Integer availableStock;

    private BookStatus status;

    private Date deletedAt;

}
