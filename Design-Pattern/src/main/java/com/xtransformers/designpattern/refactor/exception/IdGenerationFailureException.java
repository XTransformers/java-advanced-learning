package com.xtransformers.designpattern.refactor.exception;

/**
 * @author daniel
 * @date 2021-05-22
 */
public class IdGenerationFailureException extends Throwable {
    public IdGenerationFailureException(String message) {
        super(message);
    }

    public IdGenerationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
