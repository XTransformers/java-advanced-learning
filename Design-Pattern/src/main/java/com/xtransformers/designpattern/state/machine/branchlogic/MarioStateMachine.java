package com.xtransformers.designpattern.state.machine.branchlogic;

import com.xtransformers.designpattern.state.machine.State;

/**
 * 分支逻辑法
 *
 * @author daniel
 * @date 2021-07-24
 */
public class MarioStateMachine {

    private int score;

    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMashRoom() {
        if (State.SMALL.equals(currentState)) {
            currentState = State.SUPER;
            score += 100;
        }
    }

    public void obtainCape() {
        if (State.SMALL.equals(currentState) || State.SUPER.equals(currentState)) {
            currentState = State.CAPE;
            score += 200;
        }
    }

    public void obtainFireFlower() {
        if (State.SMALL.equals(currentState) || State.SUPER.equals(currentState)) {
            currentState = State.FIRE;
            score += 300;
        }
    }

    public void meetMonster() {
        if (State.CAPE.equals(currentState)) {
            currentState = State.SMALL;
            score -= 200;
            return;
        }
        if (State.SUPER.equals(currentState)) {
            currentState = State.SMALL;
            score -= 100;
            return;
        }
        if (State.FIRE.equals(currentState)) {
            currentState = State.SMALL;
            score -= 300;
            return;
        }
    }

    public int getScore() {
        return score;
    }

    public State getCurrentState() {
        return currentState;
    }

}
