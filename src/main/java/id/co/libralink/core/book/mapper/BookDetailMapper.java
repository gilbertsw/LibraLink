package id.co.libralink.core.book.mapper;

import id.co.libralink.core.book.model.entity.Book;
import id.co.libralink.core.book.model.response.BookDetailResponse;
import id.co.libralink.common.base.mapper.ToTargetMapper;

public class BookDetailMapper implements ToTargetMapper<Book, BookDetailResponse> {

    @Override
    public BookDetailResponse toTarget(Book source, Object... args) {
        return BookDetailResponse.builder()
                .id(source.getId())
                .title(source.getTitle())
                .author(source.getAuthor())
                .isbn(source.getIsbn())
                .publisher(source.getPublisher())
                .description(source.getDescription())
                .totalStock(source.getTotalStock())
                .availableStock(source.getAvailableStock())
                .status(source.getStatus())
                .deletedAt(source.getDeletedAt())
                .build();
    }

}
