package com.xtransformers.chapter9.diamond;

public class I extends H implements B, A {

    // 这里必须显式覆写 hello 方法
    @Override
    public void hello() {

    }

}
