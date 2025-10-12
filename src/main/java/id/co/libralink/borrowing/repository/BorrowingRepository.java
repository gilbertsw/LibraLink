package id.co.libralink.borrowing.repository;

import id.co.libralink.book.model.entity.Book;
import id.co.libralink.borrowing.model.entity.Borrowing;
import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.common.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends BaseRepository<Borrowing, Long> {

    Optional<Borrowing> findByUserAndReturnDateIsNull(User user);

    List<Borrowing> findByBookAndReturnDateIsNull(Book book);

    List<Borrowing> findByDueDateBeforeAndReturnDateIsNull(Date dueDate);

}
