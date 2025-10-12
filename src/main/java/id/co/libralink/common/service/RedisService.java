package id.co.libralink.common.service;

import java.time.Duration;

public interface RedisService {

    void save(String key, Object value, Duration ttl);

    void save(String key, Object value);

    <T> T get(String key, Class<T> clazz);

    void delete(String key);

    boolean exists(String key);

}
