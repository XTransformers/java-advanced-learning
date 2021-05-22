package com.xtransformers.designpattern.testability;

/**
 * @author daniel
 * @date 2021-05-18
 */
public class RedisDistributedLock {

    private RedisDistributedLock() {

    }

    public static RedisDistributedLock getSingletonIntance() {
        return InnerClass.INSTANCE;
    }

    public boolean lockTransction(String id) {
        return true;
    }

    public void unlockTransction(String id) {
    }

    private static class InnerClass {
        private static final RedisDistributedLock INSTANCE = new RedisDistributedLock();
    }
}
