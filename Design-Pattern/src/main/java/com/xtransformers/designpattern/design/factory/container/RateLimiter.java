package com.xtransformers.designpattern.design.factory.container;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class RateLimiter {

    private RedisCounter redisCounter;

    public RateLimiter(RedisCounter redisCounter) {
        this.redisCounter = redisCounter;
    }

    public void test() {
        System.out.println("Hello world.");
    }
}
