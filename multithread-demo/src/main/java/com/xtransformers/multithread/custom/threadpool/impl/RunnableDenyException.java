package com.xtransformers.multithread.custom.threadpool.impl;

/**
 * @author daniel
 * @date 2021-11-30
 */
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message) {
        super(message);
    }
}
