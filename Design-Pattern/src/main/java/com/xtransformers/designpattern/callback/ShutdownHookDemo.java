package com.xtransformers.designpattern.callback;

/**
 * @author daniel
 * @date 2021-07-02
 */
public class ShutdownHookDemo {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }

    private static class ShutdownHook extends Thread {
        @Override
        public void run() {
            System.out.println("I'm called during shuting down.");
        }
    }
}
