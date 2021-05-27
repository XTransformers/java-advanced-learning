package com.xtransformers.designpattern.design.singleton.impl.sic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author daniel
 * @date 2021-05-27
 */
public class IdGenerator {
    private AtomicLong atomicLong = new AtomicLong();

    private IdGenerator() {

    }

    private static class SingletonHolder {
        private static final IdGenerator INSTANCE = new IdGenerator();
    }

    public static IdGenerator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public long getId() {
        return atomicLong.getAndIncrement();
    }

}
