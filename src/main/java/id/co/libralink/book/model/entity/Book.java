package id.co.libralink.book.model.entity;

import id.co.libralink.book.enums.BookStatus;
import id.co.libralink.common.base.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "books",
        uniqueConstraints = @UniqueConstraint(name = "UNIQUE_TITLE_AUTHOR", columnNames = {"title", "author"}))
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column
    private String isbn;

    @Lob
    private String description;

    @Column(name = "total_stock", nullable = false)
    private Integer totalStock;

    @Column(name = "available_stock", nullable = false)
    private Integer availableStock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;

    @Column(name = "deleted_at")
    private Date deletedAt;

}
