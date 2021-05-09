package com.transformers.transactional.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author daniel
 * @date 2021-05-09
 */
@Service
public class OrderService {

    @Transactional(propagation = Propagation.REQUIRED, timeout = 30)
    public void a() {
        // 直接写，方法本身设置的事务不生效
        b();
        c();

        // 使用代理对象请求
        OrderService orderService = (OrderService) AopContext.currentProxy();
        orderService.a();
        orderService.b();
    }

    /**
     * 这里超时 5s 不生效，会继承 a() 的 30s 超时
     */
    @Transactional(propagation = Propagation.REQUIRED, timeout = 5)
    public void b() {

    }

    /**
     * 因为 c 事务是新的，超时时间为自己设置的 10s
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 10)
    public void c() {

    }

}
