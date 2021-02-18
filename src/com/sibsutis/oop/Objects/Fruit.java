package com.sibsutis.oop.Objects;

import com.sibsutis.oop.TheGame;

import java.awt.*;

public abstract class Fruit {
    protected int posX;
    protected int posY;

    public Fruit(int x, int y) {

        this.posX = x;
        this.posY = y;
    }


    public static Fruit getRandomInstance() {
        int x = (int) (Math.random() * TheGame.WIDTH);
        int y = (int) (Math.random() * TheGame.HEIGHT);

        //return new Apple(x, y);
        if ((TheGame.score % 5 == 0) && TheGame.score != 0) return new Orange(x, y);
        else return new Apple(x, y);
    }


    public abstract void paint(Graphics g);

    public boolean isCollision(int x, int y) {
        return (this.posX == x && this.posY == y);
    }

}
