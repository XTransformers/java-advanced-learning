package com.xtransformers.designpattern.visitor.singledispatch;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class DemoMain {

    public static void main(String[] args) {
        SingleDistapchClass demo = new SingleDistapchClass();
        ParentClass parent = new ChildClass();
        // 执行哪个对象的方法，由对象的实际类型决定（运行时类型）
        demo.polymorphismFunction(parent);
        // 执行哪个对象的方法，由对象的声明类型决定（编译时类型）
        demo.overloadFunction(parent);
        /**
         * ChildClass.f
         * SingleDistapchClass.overloadFunction(ParentClass parentClass)
         */
    }
}
