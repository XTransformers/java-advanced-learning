package com.xtransformers.designpattern.design.singleton.log;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;

/**
 * @author daniel
 * @date 2021-05-25
 */
public class LoggerV2 {

    private FileWriter writer;

    private static final LoggerV2 INSTANCE = new LoggerV2();

    @SneakyThrows
    private LoggerV2() {
        File file = new File("/Users/daniel/file.log");
        writer = new FileWriter(file, true);
    }

    public static LoggerV2 getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public void log(String message) {
        writer.write(message);
    }
}
