package com.xtransformers.designpattern.visitor.singledispatch;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class SingleDistapchClass {

    public void polymorphismFunction(ParentClass parentClass) {
        parentClass.f();
    }

    public void overloadFunction(ParentClass parentClass) {
        System.out.println("SingleDistapchClass.overloadFunction(ParentClass parentClass)");
    }

    public void overloadFunction(ChildClass childClass) {
        System.out.println("SingleDistapchClass.overloadFunction(ChildClass childClass)");
    }
}
