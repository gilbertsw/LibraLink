package id.co.libralink.common.base.mapper;

import java.util.Collection;

public interface ToTargetMapper<S, T> {

    T toTarget(S source, Object... args);

    default Collection<T> toTarget(Collection<S> sources, Object... args) {
        return sources.stream().map(s -> toTarget(s, args)).toList();
    }

}
