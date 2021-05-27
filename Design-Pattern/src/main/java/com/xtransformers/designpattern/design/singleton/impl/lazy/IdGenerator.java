package com.xtransformers.designpattern.design.singleton.impl.lazy;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 2. 懒汉式
 * @author daniel
 * @date 2021-05-27
 */
public class IdGenerator {
    private AtomicLong atomicLong = new AtomicLong(0);
    private static IdGenerator instance;

    private IdGenerator() {

    }

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public long getId() {
        return atomicLong.getAndIncrement();
    }

}
