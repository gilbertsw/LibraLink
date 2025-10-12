package id.co.libralink.book.mapper;

import id.co.libralink.book.model.entity.Book;
import id.co.libralink.book.model.request.BookRequest;
import id.co.libralink.common.base.mapper.FromTargetMapper;

public class BookRequestMapper implements FromTargetMapper<Book, BookRequest> {

    @Override
    public Book fromTarget(BookRequest target, Object... args) {
        return Book.builder()
                .author(target.getAuthor())
                .title(target.getTitle())
                .publisher(target.getPublisher())
                .description(target.getDescription())
                .isbn(target.getIsbn())
                .totalStock(target.getTotalStock())
                .availableStock(target.getAvailableStock())
                .status(target.getStatus())
                .build();
    }
}
