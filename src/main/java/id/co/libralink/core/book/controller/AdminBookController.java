package id.co.libralink.core.book.controller;

import id.co.libralink.core.book.model.request.BookRequest;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.url.v1}${endpoint.admin.book}")
@Tag(name = "Books", description = "Admin Book APIs")
public class AdminBookController extends BaseController {

    private final BookService bookService;

    @Operation(summary = "Get All and Search Books")
    @GetMapping
    public DefaultResponse<Page<BookListResponse>> getAllBooks(@Valid SearchBookRequest bookRequest) {
        log.info("[RequestId : {}] Execute AdminBookController.getAllBooks()", MDC.get(MDCKey.REQUEST_ID.name()));
        Page<BookListResponse> resp = bookService.getAllBooks(bookRequest);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Get Book Detail by ID")
    @GetMapping("${endpoint.book.id}")
    public DefaultResponse<BookDetailResponse> getBookById(@PathVariable("id") Long id) {
        log.info("[RequestId : {}] Execute AdminBookController.getBookById()", MDC.get(MDCKey.REQUEST_ID.name()));
        BookDetailResponse resp =  bookService.getBookById(id);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Create Book")
    @PostMapping
    public DefaultResponse<BookDetailResponse> createBook(@Validated @RequestBody BookRequest request) {
        log.info("[RequestId : {}] Execute AdminBookController.createBook()", MDC.get(MDCKey.REQUEST_ID.name()));
        BookDetailResponse resp =  bookService.createBook(request);
        return responseService.build(HttpStatus.CREATED, CommonSuccessCode.SUCCESS, resp);
    }

    @Operation(summary = "Update Book by ID")
    @PutMapping("${endpoint.book.id}")
    public DefaultResponse<BookDetailResponse> updateBook(@PathVariable("id") Long id,
                                                          @Validated @RequestBody BookRequest request) {
        log.info("[RequestId : {}] Execute AdminBookController.updateBook()", MDC.get(MDCKey.REQUEST_ID.name()));
        BookDetailResponse resp =  bookService.updateBook(id, request);
        return responseService.build(HttpStatus.OK, CommonSuccessCode.SUCCESS, resp);
    }

}
