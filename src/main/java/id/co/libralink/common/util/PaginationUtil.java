package id.co.libralink.common.util;

import id.co.libralink.common.exception.BaseException;
import id.co.libralink.common.enums.CommonErrorCode;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class PaginationUtil {

    public static final Integer MIN_PAGINATION_PAGE = 1;
    public static final Integer MIN_PAGINATION_SIZE = 1;

    public static final Integer DEFAULT_PAGINATION_PAGE = 1;
    public static final Integer DEFAULT_PAGINATION_SIZE = 10;

    private PaginationUtil() {
        // can't instantiate
    }

    public static <T, K> Page<T> map(Page<K> page, Function<? super K, ? extends T> mapper) {
        List<T> list = page.getContent().stream().map(mapper).collect(Collectors.toList());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    public static PageRequest createPageRequest(Integer page, Integer size, Sort sort) {
        validatePagingParam(page, size);
        return PageRequest.of(page - 1, size, sort);
    }

    public static Sort getSort(String sort) {
        if (StringUtil.isBlank(sort)) {
            return Sort.unsorted();
        }

        String[] propAndDirection = sort.split(",", 2);

        if (propAndDirection.length > 1) {
            return Sort.by(Sort.Direction.fromString(propAndDirection[1]), propAndDirection[0]);
        }

        return Sort.by(Sort.Direction.ASC, propAndDirection[0]);
    }

    public static Sort getSort(String[] sorts) {
        Sort sort = Sort.unsorted();

        if (ArrayUtils.isEmpty(sorts)) {
            return sort;
        }

        if (sorts.length == 2) {
            if (sorts[1].equalsIgnoreCase("asc") || sorts[1].equalsIgnoreCase("desc")) {
                return getSort(String.format("%s,%s", sorts[0], sorts[1]));
            }
        }

        for (String strSort : sorts) {
            if (StringUtil.isBlank(strSort)) {
                continue;
            }
            sort = sort.and(getSort(strSort));
        }

        return sort;
    }

    private static void validatePagingParam(int page, int size) {
        if (page < MIN_PAGINATION_PAGE) {
            throw new BaseException(CommonErrorCode.PARAM_PAGE_INVALID);
        }

        if (size < MIN_PAGINATION_SIZE) {
            throw new BaseException(CommonErrorCode.PARAM_SIZE_INVALID);
        }
    }

}
