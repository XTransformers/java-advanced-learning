package com.xtransformers.multithread.bulking.init;

/**
 * @author daniel
 * @date 2021-10-13
 */
public class InitTest {
    boolean inited = false;
    synchronized void init() {
        if (inited) {
            return;
        }
        doInit();
        inited = true;
    }

    private void doInit() {
    }
}
