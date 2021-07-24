package com.xtransformers.designpattern.state.machine.statepattern;

import com.xtransformers.designpattern.state.machine.State;

/**
 * @author daniel
 * @date 2021-07-24
 */
public class CapeMario implements IMario {

    private MarioStateMachine3 stateMachine;

    public CapeMario(MarioStateMachine3 marioStateMachine) {
        this.stateMachine = marioStateMachine;
    }

    @Override
    public State getName() {
        return State.CAPE;
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
        stateMachine.setScore(stateMachine.getScore() - 200);
    }
}
