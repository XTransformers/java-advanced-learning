package com.xtransformers.designpattern.chain.word;

/**
 * @author daniel
 * @date 2021-07-18
 */
public class PoliticalWordFilter implements SensitiveWordFilter {
    @Override
    public boolean doFilter(String content) {
        boolean legal = true;

        return legal;
    }
}
