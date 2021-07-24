package com.xtransformers.designpattern.state.machine.lookuptable;

/**
 * @author daniel
 * @date 2021-07-24
 */
public enum Event {
    GOT_MUSHROOM(0),
    GOT_CAPE(1),
    GOT_FIRE(2),
    GOT_MONSTER(3);

    private int value;

    private Event(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
