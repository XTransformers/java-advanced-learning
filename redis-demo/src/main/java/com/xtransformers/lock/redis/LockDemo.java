package com.xtransformers.lock.redis;

import com.hazelcast.org.apache.commons.codec.binary.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-09-29
 */
public class LockDemo {

    private static final Logger logger = LoggerFactory.getLogger(LockDemo.class);

    private RedisTemplate<String, String> redisTemplate;

    private Redisson redisson;

    String lockKey = "lock_key";
    String clientId = UUID.randomUUID().toString();

    public void redisLock() {
        // 使用线程给锁续租
        new Thread(() -> {
            String lockValueRedis = redisTemplate.opsForValue().get(lockKey);
            while (StringUtils.equals(lockValueRedis, clientId)) {
                redisTemplate.expire(lockKey, 10, TimeUnit.SECONDS);
            }
        }).start();

        try {
            // 设置超时时间，防止锁永远解不开
            Boolean isGetLock = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            if (!isGetLock) {
                logger.info("get lock failed.");
                return;
            }
            // 正常业务
        } finally {
            // 解锁时，只解自己的锁，不要把其他线程的锁解了
            String lockValueRedis = redisTemplate.opsForValue().get(lockKey);
            if (StringUtils.equals(lockValueRedis, clientId)) {
                redisTemplate.delete(lockKey);
            }
        }
    }

    public void redissonLock() {
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock(30, TimeUnit.SECONDS);
            // 业务
        } finally {
            lock.unlock();
        }
    }

    public void redLock() {
        RLock lock = redisson.getLock(lockKey);
//        RedissonRedLock redissonRedLock = new RedissonRedLock(lock);
        RLock redLock = redisson.getRedLock(lock);
        try {
            redLock.lock(30, TimeUnit.SECONDS);
            // 业务
        } finally {
            redLock.unlock();
        }
    }
}
