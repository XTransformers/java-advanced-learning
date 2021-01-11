package com.xtransformers.redis.redission.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import static com.xtransformers.redis.Constants.*;

/**
 * 先运行 LockDemoThread3，再运行 LockDemoThread2
 * LockDemoThread2 主线程被阻塞（因为一直无法获取到锁）
 * 当 LockDemoThread3 被终止，锁超时后，LockDemoThread2 获取到锁时，才能继续执行。
 */
public class LockDemoThread3 {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_SCHEMA_PREFIX + REDIS_HOST + ":" + REDIS_PORT_6379);

        RedissonClient client = Redisson.create(config);

        RMap<String, String> rmap = client.getMap("rmap");

        RLock lock = client.getLock("rlock");

        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                rmap.put("key:" + i, "value:" + i);
            }

            for (; ; ) {
                Thread.sleep(2000);
                System.out.println(rmap.get("key:5"));
            }
        } finally {
            lock.unlock();
        }
    }

}
