package com.xtransformers.designpattern.design.factory.container;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class RedisCounter {

    private String ipAddress;
    private int port;

    public RedisCounter(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }
}
