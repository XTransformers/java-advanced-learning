package com.xtransformers.designpattern.chain.four;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author daniel
 * @date 2021-07-18
 */
public class HandlerChain {
    private List<IHandler> handlerList = Lists.newArrayList();

    public void handle() {
        for (IHandler handler : handlerList) {
            // 所有处理器都处理一遍
            handler.handle();
        }
    }

    public void addHandler(IHandler handler) {
        handlerList.add(handler);
    }
}
