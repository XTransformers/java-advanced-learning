package com.xtransformers.designpattern.design.singleton.impl.enu;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 5. 枚举
 *
 * @author daniel
 * @date 2021-05-27
 */
public enum IdGenerator {
    INSTANCE;
    private AtomicLong atomicLong = new AtomicLong(0);

    public long getId() {
        return atomicLong.getAndIncrement();
    }
}
