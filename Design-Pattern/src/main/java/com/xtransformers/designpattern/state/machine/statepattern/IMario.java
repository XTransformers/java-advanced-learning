package com.xtransformers.designpattern.state.machine.statepattern;

import com.xtransformers.designpattern.state.machine.State;

/**
 * @author daniel
 * @date 2021-07-24
 */
public interface IMario {
    State getName();

    void obtainMushRoom();

    void obtainCape();

    void obtainFireFlower();

    void meetMonster();
}
