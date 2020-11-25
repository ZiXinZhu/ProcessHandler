package com.zzx.transactions.utils.redission;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@Slf4j
public class RedisssonImpl implements RedissonUtil {
    @Autowired
    private RedissonClient redissonClient;
    private String LOCK_TITLE = "chagoi:lock:";

    @Override
    public <T> T acquireLock(String key, Supplier<T> loadBack)  {
        T load ;
        try {
            acquire(key);
            load = loadBack.get();
        }finally{
            release(key);
        }
        return load;
    }

    @Override
    public void acquire(String lockName) {
        String key = LOCK_TITLE + lockName;
        RLock mylock = redissonClient.getLock(key);
        // lock提供带timeout参数，timeout结束强制解锁，防止死锁
        mylock.lock(2, TimeUnit.MINUTES);
        // RedissonManager.nextID();
        log.info("======redis锁开启======" + Thread.currentThread().getName());
    }

    @Override
    public void release(String lockName) {
        String key = LOCK_TITLE + lockName;
        // 先获取锁
        RLock mylock = redissonClient.getLock(key);
        // 再释放锁
        mylock.unlock();
        log.info("======redis锁释放(unlock)======" + Thread.currentThread().getName());
    }

}

