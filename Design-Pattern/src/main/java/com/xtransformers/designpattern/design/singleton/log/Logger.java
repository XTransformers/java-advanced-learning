package com.xtransformers.designpattern.design.singleton.log;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;

/**
 * @author daniel
 * @date 2021-05-25
 */
public class Logger {

    private FileWriter writer;

    @SneakyThrows
    public Logger() {
        // 竞争资源
        File file = new File("/Users/daniel/log.txt");
        writer = new FileWriter(file, true);
    }

    @SneakyThrows
    public void log(String message) {
        // 这里把对象级锁换成类对象锁
        synchronized (Logger.class) {
            // write 方法已经增加了对象级锁
            writer.write(message);
        }
    }

}
