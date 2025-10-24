package id.co.libralink.core.book.model.request;

import id.co.libralink.core.book.enums.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "error.param.empty")
    private String title;

    @NotBlank(message = "error.param.empty")
    private String author;

    @NotBlank(message = "error.param.empty")
    private String publisher;

    @NotBlank(message = "error.param.empty")
    private String isbn;

    @NotBlank(message = "error.param.empty")
    private String description;

    @NotNull(message = "error.param.empty")
    private Integer totalStock;

    @NotNull(message = "error.param.empty")
    private Integer availableStock;

    @NotNull(message = "error.param.empty")
    private BookStatus status;

}
