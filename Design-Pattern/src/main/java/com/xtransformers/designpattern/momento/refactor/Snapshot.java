package com.xtransformers.designpattern.momento.refactor;

/**
 * @author daniel
 * @date 2021-08-14
 */
public class Snapshot {

    private String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
