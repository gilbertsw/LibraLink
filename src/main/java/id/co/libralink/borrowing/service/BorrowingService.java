package id.co.libralink.borrowing.service;

import id.co.libralink.borrowing.model.entity.Borrowing;
import id.co.libralink.borrowing.model.request.BorrowBookRequest;
import id.co.libralink.borrowing.model.request.SearchBorrowingRequest;
import id.co.libralink.borrowing.model.response.BorrowingDetailResponse;
import id.co.libralink.borrowing.model.response.BorrowingListResponse;
import id.co.libralink.common.base.service.BaseService;
import org.springframework.data.domain.Page;

public interface BorrowingService extends BaseService<Borrowing> {

    Page<BorrowingListResponse> getAllBorrowing(SearchBorrowingRequest request);

    BorrowingDetailResponse getBorrowingById(Long id);

    BorrowingDetailResponse borrowBook(BorrowBookRequest request);

    BorrowingDetailResponse returnBook(Long borrowingId, Long userId);

}
