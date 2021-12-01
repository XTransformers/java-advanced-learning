package com.xtransformers.multithread.jvm.init;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author daniel
 * @date 2021-12-02
 */
public class ClassInitMulti {
    static {
        try {
            System.out.println("The ClassInitMulti static code block will be invoke.");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(10, 15)
                .forEach(index -> new Thread(ClassInitMulti::new));

//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> new ClassInitMulti()).start();
//        }
    }
}
