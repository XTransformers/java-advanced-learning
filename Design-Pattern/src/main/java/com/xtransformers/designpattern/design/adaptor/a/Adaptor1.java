package com.xtransformers.designpattern.design.adaptor.a;

/**
 * 类适配器：基于继承
 *
 * @author daniel
 * @date 2021-06-08
 */
public class Adaptor1 extends Adaptee implements ITarget {

    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        // 重新实现
    }

    // 不需要实现 fc() 直接继承自父类 与对象适配器最大的不同
}
