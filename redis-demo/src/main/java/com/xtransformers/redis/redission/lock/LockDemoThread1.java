package com.xtransformers.redis.redission.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import static com.xtransformers.redis.Constants.*;

/**
 * 先运行 LockDemoThread1，再运行 LockDemoThread2
 * 每2秒输出一次的rmap.get("key:5")，会从 value:5 变为 value:105
 */
public class LockDemoThread1 {

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
        } finally {
            lock.unlock();
        }

        for (; ; ) {
            Thread.sleep(2000);
            System.out.println(rmap.get("key:5"));
        }

    }

}
