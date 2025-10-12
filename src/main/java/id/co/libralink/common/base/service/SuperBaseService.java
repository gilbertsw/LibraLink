package id.co.libralink.common.base.service;

import id.co.libralink.common.query.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SuperBaseService<T> {

    Page<T> findAll(SearchQuery<T> query, Pageable pageable);

    Optional<T> findById(Long id);

}
