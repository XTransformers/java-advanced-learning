package com.xtransformers.multithread.threadlocal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ThreadLocal 示例代码
 *
 * @author daniel
 * @date 2021-10-11
 */
public class ThreadId {

    static final AtomicLong nextId = new AtomicLong(0);

    static final ThreadLocal<Long> tl = ThreadLocal.withInitial(nextId::getAndIncrement);

    static long get() {
        return tl.get();
    }
}
