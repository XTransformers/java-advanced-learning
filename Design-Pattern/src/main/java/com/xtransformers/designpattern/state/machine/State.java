package com.xtransformers.designpattern.state.machine;

/**
 * @author daniel
 * @date 2021-07-24
 */
public enum State {

    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3);

    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
