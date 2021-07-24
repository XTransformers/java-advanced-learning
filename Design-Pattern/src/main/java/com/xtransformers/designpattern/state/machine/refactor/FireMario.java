package com.xtransformers.designpattern.state.machine.refactor;

import com.xtransformers.designpattern.state.machine.State;

/**
 * @author daniel
 * @date 2021-07-24
 */
public class FireMario implements IMario {

    private static final FireMario instance = new FireMario();

    private FireMario() {
    }

    public static FireMario getInstance() {
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
