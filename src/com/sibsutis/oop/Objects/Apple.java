package com.sibsutis.oop.Objects;

import com.sibsutis.oop.TheGame;

import java.awt.*;

import static com.sibsutis.oop.TheGame.SCALE;
import static com.sibsutis.oop.TheGame.SHIFT;


public class Apple extends Fruit {

    public Apple(int x, int y) {
        super(x, y);
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(this.posX * SCALE + 4 + SHIFT, this.posY * SCALE + 4 + SHIFT, SCALE - 8, SCALE - 8);
    }
}
