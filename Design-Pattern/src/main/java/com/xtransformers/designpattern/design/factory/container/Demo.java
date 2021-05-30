package com.xtransformers.designpattern.design.factory.container;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class Demo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        rateLimiter.test();
    }
}
