package com.xtransformers.designpattern.refactor;

import com.xtransformers.designpattern.refactor.exception.IdGenerationFailureException;

/**
 * @author daniel
 * @date 2021-05-22
 */
public interface IdGenerator {
    String generate() throws IdGenerationFailureException;
}
