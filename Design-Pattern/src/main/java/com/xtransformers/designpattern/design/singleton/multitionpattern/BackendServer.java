package com.xtransformers.designpattern.design.singleton.multitionpattern;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;

/**
 * 多例模式
 *
 * @author daniel
 * @date 2021-05-28
 */
public class BackendServer {

    private Long serverNo;
    private String serverAddress;

    private static final int SERVER_COUNT = 3;
    private static final Map<Long, BackendServer> serverInstances = Maps.newHashMap();

    static {
        serverInstances.put(1L, new BackendServer(1L, "192.134.22.138:8080"));
        serverInstances.put(2L, new BackendServer(2L, "192.134.22.139:8080"));
        serverInstances.put(3L, new BackendServer(3L, "192.134.22.140:8080"));
    }

    private BackendServer(Long serverNo, String serverAddress) {
        this.serverNo = serverNo;
        this.serverAddress = serverAddress;
    }

    public BackendServer getInstance(Long serverNo) {
        return serverInstances.get(serverNo);
    }

    public BackendServer getRandomInstance() {
        Random random = new Random();
        int key = random.nextInt(SERVER_COUNT) + 1;
        return serverInstances.get(key);
    }
}
