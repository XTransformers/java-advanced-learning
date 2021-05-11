package com.xtransformers.chapter14.pattern;

/**
 * @author daniel
 * @date 2021-05-11
 */
public class BinOp extends Expr {
    private String opname;

    private Expr left;

    private Expr right;

    public BinOp() {
    }

    public BinOp(String opname, Expr left, Expr right) {
        this.opname = opname;
        this.left = left;
        this.right = right;
    }

    public String getOpname() {
        return opname;
    }

    public Expr getLeft() {
        return left;
    }

    public Expr getRight() {
        return right;
    }
}
