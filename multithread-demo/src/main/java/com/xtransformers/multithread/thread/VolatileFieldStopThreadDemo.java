package com.xtransformers.multithread.thread;

import java.util.concurrent.TimeUnit;

/**
 * 使用 volatile 变量优雅退出线程
 *
 * @author daniel
 * @date 2021-11-11
 */
public class VolatileFieldStopThreadDemo {
    static class CustomTask extends Thread {
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work.");
            while (!closed && !isInterrupted()) {
                // 执行逻辑
            }
            System.out.println("I will be exiting.");
        }

        public void close() {
            closed = true;
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CustomTask task = new CustomTask();
        task.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown.");
        task.close();
    }
}
