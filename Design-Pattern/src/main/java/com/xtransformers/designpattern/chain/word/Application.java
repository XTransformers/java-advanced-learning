package com.xtransformers.designpattern.chain.word;

/**
 * @author daniel
 * @date 2021-07-18
 */
public class Application {
    public static void main(String[] args) {
        SensitiveWordFilterChain chain = new SensitiveWordFilterChain();
        chain.addFilter(new SexyWordFilter());
        chain.addFilter(new PoliticalWordFilter());
        chain.addFilter(new AdsWordFilter());
        boolean legal = chain.filter("");
        if (legal) {
            // publish
        } else {
            // don't publish
        }
    }
}
