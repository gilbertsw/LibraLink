package id.co.libralink.common.model.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.libralink.common.util.PaginationUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest implements Serializable {

    private Integer page;

    private Integer size;

    private String[] sorts = new String[]{}; // e.g: sorts=created_date,asc&sorts=status,desc

    public Integer getPage() {
        if (page == null || page < PaginationUtil.MIN_PAGINATION_PAGE) {
            page = PaginationUtil.DEFAULT_PAGINATION_PAGE;
        }
        return page;
    }

    public Integer getSize() {
        if (size == null || size < PaginationUtil.MIN_PAGINATION_SIZE) {
            size = PaginationUtil.DEFAULT_PAGINATION_SIZE;
        }
        return size;
    }

    @JsonIgnore
    public Pageable getPageable() {
        return PaginationUtil.createPageRequest(getPage(), getSize(), PaginationUtil.getSort(sorts));
    }

}
