package com.zzx.transactions.utils.redission;

import java.util.function.Supplier;

public interface RedissonUtil {
    <T> T acquireLock(String key, Supplier<T> loadBack);
    void acquire(String lockName);
    void release(String lockName);
}
