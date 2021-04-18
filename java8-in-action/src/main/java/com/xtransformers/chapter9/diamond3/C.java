package com.xtransformers.chapter9.diamond3;

public class C implements B, A {
    // 几乎完全一样的函数签名
    // 返回值写成 Number 会编译报错
    @Override
    public Integer getNumber() {
        return null;
    }
}
