package id.co.libralink.borrowing.mapper;

import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.book.model.entity.Book;
import id.co.libralink.borrowing.model.entity.Borrowing;
import id.co.libralink.borrowing.model.response.BorrowingDetailResponse;
import id.co.libralink.borrowing.model.response.BorrowingListResponse;
import id.co.libralink.common.base.mapper.ToTargetMapper;

public class BorrowingListMapper implements ToTargetMapper<Borrowing, BorrowingListResponse> {

    @Override
    public BorrowingListResponse toTarget(Borrowing source, Object... args) {
        User user = source.getUser();
        Book book = source.getBook();
        return BorrowingListResponse.builder()
                .id(source.getId())
                .userName(user.getName())
                .userEmail(user.getEmail())
                .bookTitle(book.getTitle())
                .bookAuthor(book.getAuthor())
                .bookAuthor(book.getAuthor())
                .status(source.getStatus())
                .build();
    }

}
