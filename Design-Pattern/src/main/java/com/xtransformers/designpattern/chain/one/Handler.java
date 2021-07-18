package com.xtransformers.designpattern.chain.one;

/**
 * @author daniel
 * @date 2021-07-06
 */
public abstract class Handler {

    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public final void handle() {
        boolean handled = doHandle();
        // 被某个处理器处理后，终止
        if (!handled && successor != null) {
            successor.handle();
        }
    }

    public abstract boolean doHandle();

}
