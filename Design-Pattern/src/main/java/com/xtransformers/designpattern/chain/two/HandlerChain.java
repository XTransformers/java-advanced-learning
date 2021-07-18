package com.xtransformers.designpattern.chain.two;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author daniel
 * @date 2021-07-06
 */
public class HandlerChain {

    private List<IHandler> handlers = Lists.newArrayList();

    public void handle() {
        for (IHandler handler : handlers) {
            boolean handled = handler.handle();
            if (handled) {
                break;
            }
        }
    }

    public void addHandler(IHandler handler) {
        handlers.add(handler);
    }
}
