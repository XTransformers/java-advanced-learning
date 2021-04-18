package com.xtransformers.chapter9.game;

/**
 * 旋转
 */
public interface Rotatable {

    void setRotationAngle(int angleInDegrees);

    int getRotationAngle();

    default void rotateBy(int angleInDegress) {
        setRotationAngle((getRotationAngle() + angleInDegress) % 360);
    }
}
