package com.xtransformers.multithread.threadlocal.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat 不是线程安全的，多线程使其线程安全的方案之一
 *
 * @author daniel
 * @date 2021-10-11
 */
public class SafeDateFormat {

    static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));

    static DateFormat get() {
        return tl.get();
    }

}
