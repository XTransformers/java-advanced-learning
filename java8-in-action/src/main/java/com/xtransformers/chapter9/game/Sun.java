package com.xtransformers.chapter9.game;

public class Sun implements Rotatable, Movable {

    private int rotationAngle;

    private int x;

    private int y;

    @Override
    public int getRotationAngle() {
        return rotationAngle;
    }

    @Override
    public void setRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
