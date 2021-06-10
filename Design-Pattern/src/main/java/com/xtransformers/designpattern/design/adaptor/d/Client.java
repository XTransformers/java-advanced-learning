package com.xtransformers.designpattern.design.adaptor.d;

/**
 * 在我们系统中对外部系统A 的使用
 *
 * @author daniel
 * @date 2021-06-10
 */
public class Client {
    private IA a;

    public Client(IA a) {
        this.a = a;
    }
}
