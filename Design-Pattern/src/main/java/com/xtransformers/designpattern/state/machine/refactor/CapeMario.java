package com.xtransformers.designpattern.state.machine.refactor;

import com.xtransformers.designpattern.state.machine.State;

/**
 * @author daniel
 * @date 2021-07-24
 */
public class CapeMario implements IMario {

    private static final CapeMario instance = new CapeMario();

    private CapeMario() {
    }

    public static CapeMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine marioStateMachine) {
    }

    @Override
    public void obtainCape(MarioStateMachine marioStateMachine) {
    }

    @Override
    public void obtainFireFlower(MarioStateMachine marioStateMachine) {
    }

    @Override
    public void meetMonster(MarioStateMachine marioStateMachine) {
        marioStateMachine.setCurrentState(SmallMario.getInstance());
    }


}
