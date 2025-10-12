package id.co.libralink.book.service;

import id.co.libralink.book.model.entity.Book;
import id.co.libralink.book.model.request.BookRequest;
import id.co.libralink.book.model.request.SearchBookRequest;
import id.co.libralink.book.model.response.BookDetailResponse;
import id.co.libralink.book.model.response.BookListResponse;
import id.co.libralink.common.base.service.BaseService;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService extends BaseService<Book> {

    Page<BookListResponse> getAllBooks(SearchBookRequest request);

    BookDetailResponse getBookById(Long id);

    BookDetailResponse createBook(BookRequest request);

    BookDetailResponse updateBook(Long id, BookRequest request);

    Book save(Book entity);

    Optional<Book> findByTitle(String title);

}
