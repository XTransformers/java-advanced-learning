package com.xtransformers.chapter2.example;

public class RunnableDemo {
    // 用 Runnable 执行代码块

    public void test() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World.");
            }
        });

        thread = new Thread(() -> System.out.println("Hello World."));
    }

}
