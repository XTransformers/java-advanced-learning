package com.xtransformers.redis.redission.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import static com.xtransformers.redis.Constants.*;

public class LockDemoThread2 {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_SCHEMA_PREFIX + REDIS_HOST + ":" + REDIS_PORT_6379);

        RedissonClient client = Redisson.create(config);

        RLock lock = client.getLock("rlock");

        lock.lock();
        try {
            RMap<String, String> rmap = client.getMap("rmap");
            for (int i = 0; i < 10; i++) {
                rmap.put("key:" + i, "value:" + (i + 100));
            }
            System.out.println(rmap.get("key:5"));
        } finally {
            lock.unlock();
        }
        client.shutdown();
    }

}
