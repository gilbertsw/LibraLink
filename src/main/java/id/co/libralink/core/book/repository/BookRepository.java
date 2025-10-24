package id.co.libralink.core.book.repository;

import id.co.libralink.core.book.enums.BookStatus;
import id.co.libralink.core.book.model.entity.Book;
import id.co.libralink.common.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    Integer countBookByStatusIn(Collection<BookStatus> statuses);

}
