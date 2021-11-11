package com.xtransformers.multithread.thread;

import java.util.stream.IntStream;

/**
 * yield 启发式方法，不一定有效
 *
 * @author daniel
 * @date 2021-11-08
 */
public class ThreadYieldDemo {

    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(ThreadYieldDemo::create)
                .forEach(Thread::start);
    }

    public static Thread create(int index) {
        return new Thread(() -> {
            // 1 0
            if (index == 0) {
                Thread.yield();
            }
            System.out.println(index);
        });
    }
}
