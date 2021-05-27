package com.xtransformers.designpattern.design.singleton.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 唯一递增 ID 生成器
 *
 * @author daniel
 * @date 2021-05-25
 */
public class IdGenerator {

    private AtomicLong atomicLong = new AtomicLong();
    private static final IdGenerator instance = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return instance;
    }

    public long getId() {
        return atomicLong.getAndIncrement();
    }
}
