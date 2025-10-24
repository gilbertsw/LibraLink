package id.co.libralink.core.book.controller;

import id.co.libralink.core.book.model.request.SearchBookRequest;
import id.co.libralink.core.book.model.response.BookDetailResponse;
import id.co.libralink.core.book.model.response.BookListResponse;
import id.co.libralink.core.book.service.BookService;
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
@RequestMapping("${endpoint.url.v1}${endpoint.user.book}")
@Tag(name = "Books", description = "User Book APIs")
public class UserBookController extends BaseController {

    private final BookService bookService;

    @Operation(summary = "Get All and Search Books")
    @GetMapping
    public DefaultResponse<Page<BookListResponse>> getAllBooks(@Valid SearchBookRequest bookRequest) {
        log.info("[RequestId : {}] Execute UserBookController.getAllBooks()", MDC.get(MDCKey.REQUEST_ID.name()));
        Page<BookListResponse> resp = bookService.getAllBooks(bookRequest);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Get Book Detail by ID")
    @GetMapping("${endpoint.book.id}")
    public DefaultResponse<BookDetailResponse> getBookById(@PathVariable("id") Long id) {
        log.info("[RequestId : {}] Execute UserBookController.getBookById()", MDC.get(MDCKey.REQUEST_ID.name()));
        BookDetailResponse resp =  bookService.getBookById(id);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

}
