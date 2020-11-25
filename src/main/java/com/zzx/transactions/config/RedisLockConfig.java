package com.zzx.transactions.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RedisLockConfig {
    @Value("${order.redis.host}")
    private String host;
    @Value("${order.redis.port}")
    private int port;
    @Value("${order.redis.password}")
    private String password;


    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setIdleConnectionTimeout(10000)//如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。
                .setAddress("redis://"+host+":"+port)
                .setPassword(password);
        RedissonClient redissonClient = Redisson.create(config);
        RAtomicLong atomicLong = redissonClient.getAtomicLong("genId_");
        // 自增设置为从1开始
        atomicLong.set(1);
        return redissonClient;
    }
}
