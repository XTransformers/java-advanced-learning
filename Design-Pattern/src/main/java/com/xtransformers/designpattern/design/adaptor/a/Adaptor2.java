package com.xtransformers.designpattern.design.adaptor.a;

/**
 * 对象适配器：基于组合
 *
 * @author daniel
 * @date 2021-06-08
 */
public class Adaptor2 implements ITarget {

    private Adaptee adaptee;

    public Adaptor2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        adaptee.fa();
    }

    @Override
    public void f2() {
        // 重新实现
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}
