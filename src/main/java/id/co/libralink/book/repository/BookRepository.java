package id.co.libralink.book.repository;

import id.co.libralink.book.model.entity.Book;
import id.co.libralink.common.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {

    List<Book> findByAvailableTrue();

    Optional<Book> findByTitle(String title);

}
