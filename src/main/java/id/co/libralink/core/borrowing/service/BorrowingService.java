package id.co.libralink.core.borrowing.service;

import id.co.libralink.core.borrowing.enums.BorrowStatus;
import id.co.libralink.core.borrowing.model.entity.Borrowing;
import id.co.libralink.core.borrowing.model.request.BorrowBookRequest;
import id.co.libralink.core.borrowing.model.request.SearchBorrowingRequest;
import id.co.libralink.core.borrowing.model.response.BorrowingDetailResponse;
import id.co.libralink.core.borrowing.model.response.BorrowingListResponse;
import id.co.libralink.common.base.service.BaseService;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface BorrowingService extends BaseService<Borrowing> {

    Page<BorrowingListResponse> getAllBorrowing(SearchBorrowingRequest request);

    BorrowingDetailResponse getBorrowingById(Long id);

    BorrowingDetailResponse borrowBook(BorrowBookRequest request);

    BorrowingDetailResponse returnBook(Long borrowingId, Long userId);

    Integer countByStatus(Set<BorrowStatus> statuses);

}
