package com.xtransformers.chapter9.game;

public class Monster implements Rotatable, Resizable, Movable {

    private int rotationAngle;

    private int width;

    private int height;

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
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setAbsoluteSize(int width, int height) {
        this.width = width;
        this.height = height;
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

    @Override
    public String toString() {
        return "Monster{" +
                "rotationAngle=" + rotationAngle +
                ", width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
