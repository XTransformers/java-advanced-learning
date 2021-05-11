package com.xtransformers.chapter14.pattern;

/**
 * @author daniel
 * @date 2021-05-11
 */
public interface TriFunction<S, T, U, R> {

    R apply(S s, T t, U u);

}
