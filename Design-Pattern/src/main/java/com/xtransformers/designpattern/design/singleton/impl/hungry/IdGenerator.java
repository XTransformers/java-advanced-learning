package com.xtransformers.designpattern.design.singleton.impl.hungry;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式
 * fail-fast 设计原则（有问题及早暴露）
 *
 * @author daniel
 * @date 2021-05-25
 */
public class IdGenerator {

    private AtomicLong atomicLong = new AtomicLong(0);
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
