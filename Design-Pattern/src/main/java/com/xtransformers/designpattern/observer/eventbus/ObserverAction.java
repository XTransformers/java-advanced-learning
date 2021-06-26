package com.xtransformers.designpattern.observer.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 表示被 @Subscribe 注解的方法
 * 主要用于注册表中
 *
 * @author daniel
 * @date 2021-06-26
 */
public class ObserverAction {

    /**
     * 观察者类
     */
    private Object target;

    /**
     * 被注解的方法
     */
    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    /**
     * 执行被注解的方法
     *
     * @param event method 方法的参数
     */
    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
