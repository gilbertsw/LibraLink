package id.co.libralink.core.borrowing.model.request;

import id.co.libralink.core.borrowing.enums.BorrowStatus;
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
public class SearchBorrowingRequest extends PaginationRequest {

    private Long userId;

    private String userName;

    private String userEmail;

    private String bookTitle;

    private String bookAuthor;

    private String bookPublisher;

    @DateTimeFormat(pattern = DateTimeUtil.PATTERN_ISO_DATE_TIME)
    private Date borrowDateStart;

    @DateTimeFormat(pattern = DateTimeUtil.PATTERN_ISO_DATE_TIME)
    private Date borrowDateEnd;

    @DateTimeFormat(pattern = DateTimeUtil.PATTERN_ISO_DATE_TIME)
    private Date dueDateStart;

    @DateTimeFormat(pattern = DateTimeUtil.PATTERN_ISO_DATE_TIME)
    private Date dueDateEnd;

    private Set<BorrowStatus> statuses = new HashSet<>();

}
