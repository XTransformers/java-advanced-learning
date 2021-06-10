package com.xtransformers.designpattern.design.adaptor.b;

/**
 * 1. 封装有缺陷的接口设计
 *
 * @author daniel
 * @date 2021-06-08
 */
public class CDAdaptor extends CD implements ITarget {
    @Override
    public void function1() {
        CD.staticFunction1();
    }

    @Override
    public void function2() {
        super.uglyNamingFunction2();
    }

    @Override
    public void fucntion3(ParamsWrapperDefinition paramsWrapper) {
        super.tooManyParamsFunction3(1, 2, 3);
    }

    @Override
    public void function4() {
        super.lowPerformanceFunction4();
    }
}
