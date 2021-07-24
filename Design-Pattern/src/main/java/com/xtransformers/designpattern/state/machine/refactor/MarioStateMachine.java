package com.xtransformers.designpattern.state.machine.refactor;

/**
 * 状态模式
 *
 * @author daniel
 * @date 2021-07-24
 */
public class MarioStateMachine {

    private int score;

    private IMario currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    public void obtainMashRoom() {
        currentState.obtainMushRoom(this);
    }

    public void obtainCape() {
        currentState.obtainCape(this);
    }

    public void obtainFireFlower() {
        currentState.obtainFireFlower(this);
    }

    public void meetMonster() {
        currentState.meetMonster(this);
    }

    public int getScore() {
        return score;
    }

    public IMario getCurrentState() {
        return currentState;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }
}
