package id.co.libralink.common.base.mapper;

import java.util.Collection;

public interface FromTargetMapper<S, T> {

    S fromTarget(T target, Object... args);

    default Collection<S> fromTarget(Collection<T> targets, Object... args) {
        return targets.stream().map(t -> fromTarget(t, args)).toList();
    }

}
