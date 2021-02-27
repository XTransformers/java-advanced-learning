package com.xtransformers.designpattern.solid.dip.di;

public class SmsSender implements MessageSender {
    @Override
    public void send(String cellphone, String message) {
        // ...
    }
}
