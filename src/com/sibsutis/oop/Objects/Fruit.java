package com.sibsutis.oop.Objects;


import com.sibsutis.oop.TheGame;

import java.awt.*;

public abstract class Fruit {
    protected int posX;
    protected int posY;

    public  Fruit(int x, int y){

        this.posX = x;
        this.posY = y;
    }

   /* public Apple(int x, int y){
        posX = x;
        posY = y;
    }*/

    /*public void setRandomPosition(){
        posX = (int) (Math.random()* TheGame.WIDTH );
        posY = (int) (Math.random()* TheGame.HEIGHT);

    }*/


    public static Fruit getRandomInstance() {
        int x = (int) (Math.random()* TheGame.WIDTH );
        int y = (int) (Math.random()* TheGame.HEIGHT);

        return new Apple(x, y);
    }


    public abstract void paint(Graphics g);

    public boolean isCollision(int x, int y) {
        return (this.posX == x && this.posY == y);
    }

}
