package com.xtransformers.designpattern.templatemethod;

/**
 * @author daniel
 * @date 2021-06-29
 */
public class Client {

    public static void main(String[] args) {
        BClass bClass = new BClass();
        bClass.process(() -> {
            System.out.println("call back to execute");
        });
    }
}
