package com.xtransformers.designpattern.testability;

/**
 * @author daniel
 * @date 2021-05-19
 */
public class TransactionLock {

    public boolean lock(String id) {
        return RedisDistributedLock.getSingletonIntance().lockTransction(id);
    }

    public void unlock(String id) {
        RedisDistributedLock.getSingletonIntance().unlockTransction(id);
    }
}
