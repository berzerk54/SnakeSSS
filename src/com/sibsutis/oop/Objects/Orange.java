package com.sibsutis.oop.Objects;

import java.awt.*;

import static com.sibsutis.oop.TheGame.SCALE;
import static com.sibsutis.oop.TheGame.SHIFT;

public class Orange extends Fruit {

    public Orange(int x, int y) {
        super(x, y);
    }

    public void paint(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(this.posX * SCALE + 4 + SHIFT, this.posY * SCALE + 4 + SHIFT, SCALE - 8, SCALE - 8);
    }
}
