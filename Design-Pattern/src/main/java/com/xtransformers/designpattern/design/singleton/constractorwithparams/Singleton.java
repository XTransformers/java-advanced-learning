package com.xtransformers.designpattern.design.singleton.constractorwithparams;

/**
 * @author daniel
 * @date 2021-05-27
 */
public class Singleton {

    private static Singleton instance = null;

    private final String paramA;
    private final String paramB;

    private Singleton(String paramA, String paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            throw new RuntimeException("run init() first");
        }
        return instance;
    }

    public static synchronized Singleton init(String paramA, String paramB) {
        if (instance != null) {
            throw new RuntimeException("singleton has been created.");
        }
        instance = new Singleton(paramA, paramB);
        return instance;
    }

    public static void main(String[] args) {
        Singleton.init("a", "b");
        Singleton instance = Singleton.getInstance();
    }
}
