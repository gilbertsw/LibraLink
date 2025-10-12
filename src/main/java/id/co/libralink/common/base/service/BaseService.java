package id.co.libralink.common.base.service;

public interface BaseService<T> extends SuperBaseService<T> {

    T create(T entity);

    T update(T oldEntity, T newEntity);

}
