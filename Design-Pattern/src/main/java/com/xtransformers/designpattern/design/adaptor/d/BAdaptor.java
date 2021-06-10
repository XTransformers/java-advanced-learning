package com.xtransformers.designpattern.design.adaptor.d;

/**
 * 使用外部系统 B 替换 外部系统 A
 *
 * @author daniel
 * @date 2021-06-10
 */
public class BAdaptor implements IA {
    private B b;

    public BAdaptor(B b) {
        this.b = b;
    }

    @Override
    public void fa() {
        b.fb();
    }
}
