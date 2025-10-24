package id.co.libralink.core.borrowing.repository;

import id.co.libralink.core.book.model.entity.Book;
import id.co.libralink.core.borrowing.enums.BorrowStatus;
import id.co.libralink.core.borrowing.model.entity.Borrowing;
import id.co.libralink.apigateway.account.model.entity.User;
import id.co.libralink.common.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends BaseRepository<Borrowing, Long> {

    Optional<Borrowing> findByUserAndReturnDateIsNull(User user);

    List<Borrowing> findByBookAndReturnDateIsNull(Book book);

    List<Borrowing> findByDueDateBeforeAndReturnDateIsNull(Date dueDate);

    Integer countByStatusIn(Collection<BorrowStatus> statuses);
}
