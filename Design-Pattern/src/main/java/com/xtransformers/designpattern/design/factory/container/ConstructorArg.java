package com.xtransformers.designpattern.design.factory.container;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class ConstructorArg {
    private boolean isRef;

    private Class type;

    private Object arg;

    public boolean isRef() {
        return isRef;
    }

    public void setRef(boolean ref) {
        isRef = ref;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }
}
