package id.co.libralink.core.borrowing.controller;

import id.co.libralink.core.borrowing.model.request.BorrowBookRequest;
import id.co.libralink.core.borrowing.model.request.SearchBorrowingRequest;
import id.co.libralink.core.borrowing.model.response.BorrowingDetailResponse;
import id.co.libralink.core.borrowing.model.response.BorrowingListResponse;
import id.co.libralink.core.borrowing.service.BorrowingService;
import id.co.libralink.common.base.controller.BaseController;
import id.co.libralink.common.enums.CommonSuccessCode;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.web.model.DefaultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.url.v1}${endpoint.user.borrowing}")
@Tag(name = "Borrowings", description = "User Borrowing APIs")
public class UserBorrowingController extends BaseController {

    private final BorrowingService borrowingService;

    @Operation(summary = "Get All and Search Borrowings")
    @GetMapping
    public DefaultResponse<Page<BorrowingListResponse>> getAllBorrowings(@Valid SearchBorrowingRequest request) {
        log.info("[RequestId : {}] Execute UserBorrowingController.getAllBorrowings()", MDC.get(MDCKey.REQUEST_ID.name()));
        request.setUserName(null); request.setUserEmail(null);
        request.setUserId(getUserDetail().getUser().getId());
        Page<BorrowingListResponse> resp = borrowingService.getAllBorrowing(request);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Get Borrowing Detail by ID")
    @GetMapping("${endpoint.book.id}")
    public DefaultResponse<BorrowingDetailResponse> getBorrowingById(@PathVariable("id") Long id) {
        log.info("[RequestId : {}] Execute UserBorrowingController.getBorrowingById()", MDC.get(MDCKey.REQUEST_ID.name()));
        BorrowingDetailResponse resp =  borrowingService.getBorrowingById(id);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Borrow a Book")
    @PostMapping
    public DefaultResponse<BorrowingDetailResponse> borrowBook(@Validated @RequestBody BorrowBookRequest request) {
        log.info("[RequestId : {}] Execute UserBorrowingController.borrowBook()", MDC.get(MDCKey.REQUEST_ID.name()));
        BorrowingDetailResponse resp =  borrowingService.borrowBook(request);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Return Borrowed Book")
    @PostMapping("${endpoint.book.id}")
    public DefaultResponse<BorrowingDetailResponse> returnBook(@PathVariable("id") Long borrowingId) {
        log.info("[RequestId : {}] Execute UserBorrowingController.returnBook()", MDC.get(MDCKey.REQUEST_ID.name()));
        Long userId = getUserDetail().getUser().getId();
        BorrowingDetailResponse resp =  borrowingService.returnBook(borrowingId, userId);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

}
