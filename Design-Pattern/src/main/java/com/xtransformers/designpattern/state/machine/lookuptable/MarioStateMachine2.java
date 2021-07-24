package com.xtransformers.designpattern.state.machine.lookuptable;

import com.xtransformers.designpattern.state.machine.State;

import static com.xtransformers.designpattern.state.machine.State.*;

/**
 * 查表法
 *
 * @author daniel
 * @date 2021-07-24
 */
public class MarioStateMachine2 {

    private int score;

    private State currentState;

    private static final State[][] transitionTable = {
            {SUPER, CAPE, FIRE, SMALL},
            {SUPER, CAPE, FIRE, SMALL},
            {CAPE, CAPE, CAPE, SMALL},
            {FIRE, FIRE, FIRE, SMALL}
    };

    private static final int[][] actionTable = {
            {+100, +200, +300, +0},
            {+0, +200, +300, -100},
            {+0, +0, +0, -200},
            {+0, +0, +0, -300}
    };

    public MarioStateMachine2() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMashRoom() {
        execureEvent(Event.GOT_MUSHROOM);
    }

    public void obtainCape() {
        execureEvent(Event.GOT_CAPE);
    }

    public void obtainFireFlower() {
        execureEvent(Event.GOT_FIRE);
    }

    public void meetMonster() {
        execureEvent(Event.GOT_MONSTER);
    }

    private void execureEvent(Event event) {
        int stateValue = currentState.getValue();
        int eventValue = event.getValue();
        this.currentState = transitionTable[stateValue][eventValue];
        this.score += actionTable[stateValue][eventValue];
    }

    public int getScore() {
        return score;
    }

    public State getCurrentState() {
        return currentState;
    }

}
