package com.xtransformers.designpattern.chain.two;

/**
 * @author daniel
 * @date 2021-07-06
 */
public class Application {

    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.handle();
    }
}
