package id.co.libralink.core.book.model.request;

import id.co.libralink.core.book.enums.BookStatus;
import id.co.libralink.common.model.request.PaginationRequest;
import id.co.libralink.common.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookRequest extends PaginationRequest {

    private String title;

    private String author;

    private String publisher;

    private String isbn;

    @DateTimeFormat(pattern = DateTimeUtil.PATTERN_ISO_DATE_TIME)
    private Date startPeriod;

    @DateTimeFormat(pattern = DateTimeUtil.PATTERN_ISO_DATE_TIME)
    private Date endPeriod;

    private Set<BookStatus> statuses = new HashSet<>();

}
