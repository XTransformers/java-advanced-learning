package com.xtransformers.designpattern.design.singleton.constractorwithparams;

/**
 * @author daniel
 * @date 2021-05-27
 */
public class Singleton3 {
    private static Singleton3 instance = null;

    private String param1;
    private String param2;

    private Singleton3() {
        this.param1 = Config.PARAM_A;
        this.param2 = Config.PARAM_B;
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

    public synchronized static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
    }
}
