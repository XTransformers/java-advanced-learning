package com.xtransformers.multithread.custom.uncaught.exception;

import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-11-29
 */
public class CaptureThreadException {

    public static void main(String[] args) {
        // 设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });
        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            // here will throw unchecked exception.
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();
    }
}
