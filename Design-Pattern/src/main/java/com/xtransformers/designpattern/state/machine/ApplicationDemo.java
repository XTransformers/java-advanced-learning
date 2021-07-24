package com.xtransformers.designpattern.state.machine;

import com.xtransformers.designpattern.state.machine.branchlogic.MarioStateMachine;

/**
 * @author daniel
 * @date 2021-07-24
 */
public class ApplicationDemo {
    public static void main(String[] args) {
        MarioStateMachine mario = new MarioStateMachine();
        mario.obtainMashRoom();
        int score = mario.getScore();
        State currentState = mario.getCurrentState();
        System.out.println("mario score: " + score + ", state: " + currentState);
    }
}
