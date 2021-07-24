package com.xtransformers.designpattern.state.machine.statepattern;

/**
 * 状态模式
 *
 * @author daniel
 * @date 2021-07-24
 */
public class MarioStateMachine3 {

    private int score;

    private IMario currentState;

    public MarioStateMachine3() {
        this.score = 0;
        this.currentState = new SmallMario(this);
    }

    public void obtainMashRoom() {
        currentState.obtainMushRoom();
    }

    public void obtainCape() {
        currentState.obtainCape();
    }

    public void obtainFireFlower() {
        currentState.obtainFireFlower();
    }

    public void meetMonster() {
        currentState.meetMonster();
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
