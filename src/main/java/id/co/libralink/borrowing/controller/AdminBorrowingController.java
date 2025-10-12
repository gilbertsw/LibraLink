package id.co.libralink.borrowing.controller;

import id.co.libralink.borrowing.model.request.SearchBorrowingRequest;
import id.co.libralink.borrowing.model.response.BorrowingDetailResponse;
import id.co.libralink.borrowing.model.response.BorrowingListResponse;
import id.co.libralink.borrowing.service.BorrowingService;
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
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.url.v1}${endpoint.admin.borrowing}")
@Tag(name = "Borrowings", description = "Admin Borrowings APIs")
public class AdminBorrowingController extends BaseController {

    private final BorrowingService borrowingService;

    @Operation(summary = "Get All and Search Borrowings")
    @GetMapping
    public DefaultResponse<Page<BorrowingListResponse>> getAllBorrowings(@Valid SearchBorrowingRequest request) {
        log.info("[RequestId : {}] Execute AdminBorrowingController.getAllBorrowings()", MDC.get(MDCKey.REQUEST_ID.name()));
        Page<BorrowingListResponse> resp = borrowingService.getAllBorrowing(request);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Get Borrowing Detail by ID")
    @GetMapping("${endpoint.book.id}")
    public DefaultResponse<BorrowingDetailResponse> getBookById(@PathVariable("id") Long id) {
        log.info("[RequestId : {}] Execute AdminBorrowingController.getBorrowingById()", MDC.get(MDCKey.REQUEST_ID.name()));
        BorrowingDetailResponse resp =  borrowingService.getBorrowingById(id);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

}
