package id.co.libralink.book.service.impl;

import id.co.libralink.book.mapper.BookDetailMapper;
import id.co.libralink.book.mapper.BookListMapper;
import id.co.libralink.book.mapper.BookRequestMapper;
import id.co.libralink.book.model.entity.Book;
import id.co.libralink.book.model.request.BookRequest;
import id.co.libralink.book.model.request.SearchBookRequest;
import id.co.libralink.book.model.response.BookDetailResponse;
import id.co.libralink.book.model.response.BookListResponse;
import id.co.libralink.book.query.BookQuery;
import id.co.libralink.book.repository.BookRepository;
import id.co.libralink.book.service.BookService;
import id.co.libralink.common.constant.CommonConstant;
import id.co.libralink.common.query.SearchQuery;
import id.co.libralink.common.util.PaginationUtil;
import id.co.libralink.common.util.PreconditionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookDetailMapper detailMapper = new BookDetailMapper();

    private final BookListMapper listMapper = new BookListMapper();

    private final BookRequestMapper requestMapper = new BookRequestMapper();


    @Override
    public Page<BookListResponse> getAllBooks(SearchBookRequest request) {
        BookQuery query = new BookQuery()
                .titleContains(request.getTitle())
                .authorContains(request.getAuthor())
                .isbnEq(request.getIsbn())
                .createdDateBetween(request.getStartPeriod(), request.getEndPeriod())
                .statusesIn(request.getStatuses());
        Page<Book> page = findAll(query, request.getPageable());
        return PaginationUtil.map(page, listMapper::toTarget);
    }

    @Override
    public BookDetailResponse getBookById(Long id) {
        Book entity = PreconditionUtil.assertNotNull(findById(id), Book.class);
        return detailMapper.toTarget(entity);
    }

    @Override
    public BookDetailResponse createBook(BookRequest request) {
        Book entity = create(requestMapper.fromTarget(request));
        return detailMapper.toTarget(entity);
    }

    @Override
    public BookDetailResponse updateBook(Long id, BookRequest request) {
        Book oldEntity = PreconditionUtil.assertNotNull(findById(id), Book.class);
        Book newEntity = requestMapper.fromTarget(request);
        return detailMapper.toTarget(update(oldEntity, newEntity));
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Page<Book> findAll(SearchQuery<Book> query, Pageable pageable) {
        return bookRepository.findAll(query, pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book entity) {
        return bookRepository.save(entity);
    }

    @Override
    public Book create(Book entity) {
        return bookRepository.save(entity);
    }

    @Override
    public Book update(Book oldEntity, Book newEntity) {
        PreconditionUtil.assertNotNull(oldEntity, Book.class);
        BeanUtils.copyProperties(newEntity, oldEntity, CommonConstant.DEFAULT_IGNORE_PROPERTIES);
        return bookRepository.save(oldEntity);
    }
}
