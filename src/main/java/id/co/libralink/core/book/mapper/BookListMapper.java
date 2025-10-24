package id.co.libralink.core.book.mapper;

import id.co.libralink.core.book.model.entity.Book;
import id.co.libralink.core.book.model.response.BookListResponse;
import id.co.libralink.common.base.mapper.ToTargetMapper;

public class BookListMapper implements ToTargetMapper<Book, BookListResponse> {

    @Override
    public BookListResponse toTarget(Book source, Object... args) {
        return BookListResponse.builder()
                .id(source.getId())
                .title(source.getTitle())
                .author(source.getAuthor())
                .publisher(source.getPublisher())
                .status(source.getStatus())
                .build();
    }

}
