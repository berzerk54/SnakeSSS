package com.sibsutis.oop;

import com.sibsutis.oop.Objects.Apple;
import com.sibsutis.oop.Objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TheGame extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int SHIFT = 10;
    public static int SPEED = 10;
    public int score = 0;
    Snake s = new Snake(5,6,5,5);
    Apple apple = new Apple (Math.abs((int) (Math.random() * WIDTH - 1)),   Math.abs((int) (Math.random() * HEIGHT-1)));
    Timer timer = new Timer (1000/SPEED,this);
    public boolean paused=false;

    public void dialog1() {
        String[] options = {"Да", "Нет"};
        int choice = JOptionPane.showOptionDialog(null,
                "<html> <h2>Вы приехали..</h2> <br> Хотите начать сначала?",
                "Ну вот..:(",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);
        if (choice == JOptionPane.OK_OPTION) {
            restartGame();
        }
        else
        System.exit(0);
        }



    public TheGame(){
        timer.start();
        addKeyListener( new KeyBoard());
        setFocusable(true);

    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0+SHIFT,0+SHIFT,WIDTH*SCALE,HEIGHT*SCALE);
        for (int x = 0; x < WIDTH*SCALE+SHIFT ; x+=SCALE) {
            g.setColor(Color.darkGray);
            g.drawLine(x+SHIFT,0+SHIFT,x+SHIFT,(WIDTH*SCALE)+SHIFT);
        }
        for (int y = 0; y < WIDTH*SCALE+SHIFT ; y+=SCALE) {
            g.setColor(Color.darkGray);
            g.drawLine(0+SHIFT,y+SHIFT,(HEIGHT*SCALE)+SHIFT,y+SHIFT);
        }
        g.setColor(Color.red);
        g.fillOval(apple.posX*SCALE+4+SHIFT, apple.posY*SCALE+4+SHIFT,SCALE-8,SCALE-8);


        for ( int l = 1; l < s.length; l++){
            g.setColor(Color.green);
            g.fillRect((s.snakeX[l]*SCALE+2)+SHIFT,(s.snakeY[l]*SCALE+2)+SHIFT,SCALE-4,SCALE-4);
            g.setColor(Color.white);
           g.fillRect((s.snakeX[0]*SCALE+2)+SHIFT,(s.snakeY[0]*SCALE+2)+SHIFT,SCALE-4,SCALE-4);
        }

    }

    public static void main (String[] args) {
        jFrame = new JFrame("The Snake Game-9000");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(800,800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.add(new TheGame());
        jFrame.setVisible(true);
    }
    public  void restartGame(){
        s.length=2;
        timer.start();
        s.direction=0;
        apple.setRandomPosition();


    }

    public  void pause(){
        paused ^= paused;
        if (paused=true) timer.stop();
        else if (paused=false) timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();
        //apple.setRandomPosition();
        if ((s.snakeX[0] == apple.posX) && (s.snakeY[0] == apple.posY)) {
            score++;
            apple.setRandomPosition();
            s.length++;
        }

        for (int l = 1; l < s.length; l++) {
            if ((s.snakeX[l] == apple.posX) && (s.snakeY[l] == apple.posY)) {
                apple.setRandomPosition();
            }
            if ((s.snakeX[0] == s.snakeX[l]) && (s.snakeY[0] == s.snakeY[l])) {
                timer.stop();
                dialog1();
                restartGame();
            }
        }

        repaint();
    }

    public class KeyBoard extends KeyAdapter{
        public void keyPressed(KeyEvent event){
            int key= event.getKeyCode();
            if (key == KeyEvent.VK_UP && s.direction != 2) s.direction=0;
            if (key == KeyEvent.VK_DOWN && s.direction != 0) s.direction=2;
            if (key == KeyEvent.VK_RIGHT && s.direction != 3) s.direction=1;
            if (key == KeyEvent.VK_LEFT && s.direction !=1) s.direction=3;
            if (key == KeyEvent.VK_ESCAPE) System.exit(0);
            //if (key == KeyEvent.VK_P) timer.stop();
            if (key == KeyEvent.VK_SPACE) pause();

        }

    }
}
