package com.xtransformers.chapter14.pattern;

/**
 * @author daniel
 * @date 2021-05-11
 */
public class Number extends Expr{
    private int val;

    public Number() {
    }

    public Number(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
