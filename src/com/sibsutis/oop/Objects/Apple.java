package com.sibsutis.oop.Objects;

import com.sibsutis.oop.TheGame;

public class Apple {

    public int posX;
    public int posY;

    public Apple(int x, int y){
        posX = x;
        posY = y;
    }
    public void setRandomPosition(){
        posX = (int) (Math.random() * TheGame.WIDTH  );
        posY = (int) (Math.random() * TheGame.HEIGHT );
    }
}
