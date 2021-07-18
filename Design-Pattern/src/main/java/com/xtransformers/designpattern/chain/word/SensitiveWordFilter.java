package com.xtransformers.designpattern.chain.word;

/**
 * @author daniel
 * @date 2021-07-18
 */
public interface SensitiveWordFilter {
    boolean doFilter(String content);
}
