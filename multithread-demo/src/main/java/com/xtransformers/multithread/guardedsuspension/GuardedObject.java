package com.xtransformers.multithread.guardedsuspension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Guarded Suspension 模式
 *
 * @author daniel
 * @date 2021-10-12
 */
public class GuardedObject<T> {
    // 受保护的对象
    T obj;

    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout = 1;

    // 保存所有 GuardedObject
    final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    // 静态方法创建 GuardedObject
    static <K, T> GuardedObject<T> create(K key) {
        GuardedObject<T> go = new GuardedObject<>();
        gos.put(key, go);
        return go;
    }

    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject go = gos.remove(key);
        if (go != null) {
            go.onChange(obj);
        }
    }

    // 获取受保护对象
    T get(Predicate<T> p, String id) {
        lock.lock();
        long start = System.currentTimeMillis();
        try {
            // MESA 管程推荐写法
            while (!p.test(obj)) {
                // MQ 超时后移除，防止内存泄漏
                if (System.currentTimeMillis() - start > 2000) {
                    gos.remove(id);
                    break;
                }
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        // 返回非空的受保护对象
        return obj;
    }

    // 事件通知方法
    void onChange(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
