package com.xtransformers.designpattern.design.singleton.impl.dcl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 3. 双重检测
 *
 * @author daniel
 * @date 2021-05-27
 */
public class IdGenerator {
    private AtomicLong atomicLong = new AtomicLong(0);
    private static volatile IdGenerator inscance;

    private IdGenerator() {

    }

    public static IdGenerator getInstance() {
        if (inscance == null) {
            synchronized (IdGenerator.class) {
                if (inscance == null) {
                    inscance = new IdGenerator();
                }
            }
        }
        return inscance;
    }

    public long getId() {
        return atomicLong.getAndIncrement();
    }
}
