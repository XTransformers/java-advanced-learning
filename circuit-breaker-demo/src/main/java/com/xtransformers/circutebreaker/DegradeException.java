package com.xtransformers.circutebreaker;

/**
 * @author daniel
 * @date 2021-06-19
 */
public class DegradeException extends Throwable {
    public DegradeException(String message) {
        super(message);
    }
}
