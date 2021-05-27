package com.xtransformers.designpattern.design.singleton.constractorwithparams;

/**
 * @author daniel
 * @date 2021-05-27
 */
public class Singleton2 {

    private static Singleton2 instance = null;

    private String param1;
    private String param2;

    private Singleton2(String param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public static Singleton2 getInstance(String param1, String param2) {
        if (instance == null) {
            instance = new Singleton2(param1, param2);
        }
        return instance;
    }

    public synchronized static void main(String[] args) {
        Singleton2 instance1 = Singleton2.getInstance("a", "b");
        Singleton2 instance2 = Singleton2.getInstance("a1", "b1");
    }
}
