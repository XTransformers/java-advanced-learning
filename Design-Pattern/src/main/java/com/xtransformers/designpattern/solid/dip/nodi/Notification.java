package com.xtransformers.designpattern.solid.dip.nodi;

/**
 * 非依赖注入 实现方式
 */
public class Notification {

    private MessageSender messageSender;

    public Notification() {
        // 通过 new 关键字实例化对象，硬编码
        this.messageSender = new MessageSender();
    }

    public void sendMessage(String cellphone, String message) {
        this.messageSender.send(cellphone, message);
    }

}
