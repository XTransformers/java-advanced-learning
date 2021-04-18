package com.xtransformers.chapter9.game;

public class Client {
    public static void main(String[] args) {
        Monster monster = new Monster();
        monster.rotateBy(180);
        monster.moveVertically(10);
        System.out.println(monster);
    }
}
