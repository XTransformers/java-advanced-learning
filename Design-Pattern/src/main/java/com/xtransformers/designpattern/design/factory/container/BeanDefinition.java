package com.xtransformers.designpattern.design.factory.container;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class BeanDefinition {

    private String id;

    private String className;

    private List<ConstructorArg> constructorArgs = Lists.newArrayList();

    private Scope scope = Scope.SINGLETON;

    private boolean lazyInit = false;

    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }
}
