package com.xtransformers.designpattern.state.machine.refactor;

import com.xtransformers.designpattern.state.machine.State;

/**
 * @author daniel
 * @date 2021-07-24
 */
public interface IMario {
    State getName();

    void obtainMushRoom(MarioStateMachine marioStateMachine);

    void obtainCape(MarioStateMachine marioStateMachine);

    void obtainFireFlower(MarioStateMachine marioStateMachine);

    void meetMonster(MarioStateMachine marioStateMachine);
}
