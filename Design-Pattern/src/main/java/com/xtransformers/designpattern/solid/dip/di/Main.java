package com.xtransformers.designpattern.solid.dip.di;

public class Main {
    public static void main(String[] args) {
        // 使用Notification
        MessageSender messageSender = new SmsSender();
        Notification notification = new Notification(messageSender);
    }
}
