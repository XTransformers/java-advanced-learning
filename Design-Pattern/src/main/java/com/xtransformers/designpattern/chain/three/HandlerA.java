package com.xtransformers.designpattern.chain.three;

/**
 * @author daniel
 * @date 2021-07-18
 */
public class HandlerA extends Handler {
    @Override
    protected void doHandle() {
        System.out.println("HandlerA.doHandle");
    }
}
