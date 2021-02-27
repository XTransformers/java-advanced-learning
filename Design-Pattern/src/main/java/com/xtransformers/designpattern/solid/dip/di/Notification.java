package com.xtransformers.designpattern.solid.dip.di;

/**
 * 依赖注入 的实现方式
 * 依赖注入 是编写可测试性代码最有效的手段
 */
public class Notification {

    private MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        this.messageSender.send(cellphone, message);
    }
}
