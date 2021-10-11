package com.xtransformers.multithread.threadlocal.date;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author daniel
 * @date 2021-10-11
 */
public class MainDemo {

    public static void main(String[] args) {
        DateFormat dateFormat = SafeDateFormat.get();
        new Thread(() -> {
            System.out.println(dateFormat.format(new Date()));
        }, "T1").start();
        new Thread(() -> {
            System.out.println(dateFormat.format(new Date()));
        }, "T2").start();
    }
}
