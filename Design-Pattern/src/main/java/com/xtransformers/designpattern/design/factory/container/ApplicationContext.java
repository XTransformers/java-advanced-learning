package com.xtransformers.designpattern.design.factory.container;

/**
 * @author daniel
 * @date 2021-05-30
 */
public interface ApplicationContext {
    Object getBean(String beanId);
}
