package id.co.libralink.borrowing.mapper;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.book.model.entity.Book;
import id.co.libralink.borrowing.model.entity.Borrowing;
import id.co.libralink.borrowing.model.response.BorrowingDetailResponse;
import id.co.libralink.common.base.mapper.ToTargetMapper;

public class BorrowingDetailMapper implements ToTargetMapper<Borrowing, BorrowingDetailResponse> {

    @Override
    public BorrowingDetailResponse toTarget(Borrowing source, Object... args) {
        User user = source.getUser();
        Book book = source.getBook();
        return BorrowingDetailResponse.builder()
                .id(source.getId())
                .userName(user.getName())
                .userEmail(user.getEmail())
                .bookTitle(book.getTitle())
                .bookAuthor(book.getAuthor())
                .bookPublisher(book.getPublisher())
                .status(source.getStatus())
                .borrowDate(source.getBorrowDate())
                .dueDate(source.getDueDate())
                .returnDate(source.getReturnDate())
                .build();
    }

}
