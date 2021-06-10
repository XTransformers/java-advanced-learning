package com.xtransformers.designpattern.design.adaptor.d;

/**
 * @author daniel
 * @date 2021-06-10
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client(new A());
        // 借助适配器 BAdaptor，Client 类中调用 IA 接口的地方无需改动
        Client client2 = new Client(new BAdaptor(new B()));
    }
}
