package com.xtransformers.designpattern.chain.three;

/**
 * @author daniel
 * @date 2021-07-18
 */
public abstract class Handler {

    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public final void handle() {
        doHandle();
        // 请求会被所有的处理器都处理一遍，不存在中途终止的情况
        if (successor != null) {
            successor.handle();
        }
    }

    protected abstract void doHandle();

}
