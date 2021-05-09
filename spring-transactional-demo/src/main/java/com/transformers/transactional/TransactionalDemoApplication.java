package com.transformers.transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 本类互相调用事务失效解决办法
 * 1. 引入AspectJ 依赖 spring-boot-starter-aop
 * 2. 开启 @EnableAspectJAutoProxy
 * 3. 暴露代理对象 @EnableAspectJAutoProxy(exposeProxy = true)
 * 4. 获取代理对象，并强转 OrderService orderService = (OrderService) AopContext.currentProxy();
 *
 * @author daniel
 * @date 2021-05-09
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class TransactionalDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalDemoApplication.class, args);
    }

}
