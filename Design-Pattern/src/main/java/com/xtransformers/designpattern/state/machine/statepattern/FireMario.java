package com.xtransformers.designpattern.state.machine.statepattern;

import com.xtransformers.designpattern.state.machine.State;

/**
 * @author daniel
 * @date 2021-07-24
 */
public class FireMario implements IMario {

    private MarioStateMachine3 stateMachine;

    public FireMario(MarioStateMachine3 marioStateMachine) {
        this.stateMachine = marioStateMachine;
    }

    @Override
    public State getName() {
        return State.FIRE;
    }

    @Override
    public void obtainMushRoom() {
    }

    @Override
    public void obtainCape() {
    }

    @Override
    public void obtainFireFlower() {
    }

    @Override
    public void meetMonster() {
        stateMachine.setCurrentState(new SmallMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() - 300);
    }
}
