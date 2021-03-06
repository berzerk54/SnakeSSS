package com.sibsutis.oop;

import com.sibsutis.oop.Objects.Fruit;
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
    public static int score = 0;
    public boolean paused = false;

    Snake s = new Snake(5, 6, 5, 5);
    Fruit fruit = null;
    Timer timer = new Timer(1000 / SPEED, this);

    public void gameOver() {
        String[] options = {"Да", "Нет"};
        int choice = JOptionPane.showOptionDialog(null,
                "<html> <h2>Вы приехали..</h2> <br> Хотите начать сначала?</html>",
                "Ну вот..:(",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                "Да");

        if (choice == JOptionPane.OK_OPTION)
            restartGame();
        else
            System.exit(0);
    }

    public void fatSnake() {
        String[] options = {"Да", "Нет"};
        int choice = JOptionPane.showOptionDialog(null,
                "<html> Я не знаю как, но ваша змеюка пожрала все яблоки <br> Хотите начать сначала?</html>",
                "Иногда пора остановиться",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                "Да");
        if (choice == JOptionPane.OK_OPTION)
            restartGame();
        else
            System.exit(0);
    }

    public void helpme() {
        String[] options = {"ОК"};
        int choice = JOptionPane.showOptionDialog(null,
                "<html>Я-змея-змея-змея<br>" +
                        "Я ползу-ползу-ползу..<br>" +
                        "<br>" +
                        "Управление стрелками<br> " +
                        "пауза - пробел<br>" +
                        "esc-выход </html>",
                "Хелп",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                "Да");
        pause();

    }

    public void about() {
        String[] options = {"ОК"};
        int choice = JOptionPane.showOptionDialog(null,
                "<html>Разработано Волжанкиным Александром из группы ПБТ-94<br>" +
                        "Во время разработки ни одно пресмыкающееся не пострадало. </html>",
                "О программе",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                "ОК");
        pause();

    }


    public TheGame() {
        fruit = Fruit.getRandomInstance();
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);

    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 700);
        for (int x = 0; x < WIDTH * SCALE + SHIFT; x += SCALE) {
            g.setColor(Color.darkGray);
            g.drawLine(x + SHIFT, 0 + SHIFT, x + SHIFT, (WIDTH * SCALE) + SHIFT);
        }
        for (int y = 0; y < WIDTH * SCALE + SHIFT; y += SCALE) {
            g.setColor(Color.darkGray);
            g.drawLine(0 + SHIFT, y + SHIFT, (HEIGHT * SCALE) + SHIFT, y + SHIFT);
        }

        fruit.paint(g);


        for (int l = 1; l < s.length; l++) {
            g.setColor(Color.green);
            g.fillRect((s.snakeX[l] * SCALE + 2) + SHIFT, (s.snakeY[l] * SCALE + 2) + SHIFT, SCALE - 4, SCALE - 4);
            g.setColor(Color.white);
            g.fillRect((s.snakeX[0] * SCALE + 2) + SHIFT, (s.snakeY[0] * SCALE + 2) + SHIFT, SCALE - 4, SCALE - 4);
        }
        String count = "Score: " + (score);
        String hint = "f1-help  f2-about";
        String info = "(C) 2021";

        Font f = new Font("Arial", Font.BOLD, 14);
        g.setColor(Color.yellow);
        g.setFont(f);
        g.drawString(count, 650 + SHIFT, 25);
        g.drawString(hint, 660, 600);
        g.drawString(info, 660, 630 + SHIFT);


    }

    public static void main(String[] args) {
        jFrame = new JFrame("The Snake Game-9000");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(800, 700);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.add(new TheGame());
        jFrame.setVisible(true);
    }


    public void restartGame() {
        s.length = 2;
        timer.start();
        s.direction = 0;
        fruit = Fruit.getRandomInstance();
        score = 0;


    }

    public void pause() {
        paused = !paused;
        if (paused)
            timer.stop();
        else timer.start();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();

        if (fruit.isCollision(s.snakeX[0], s.snakeY[0])) {

            score++;
            fruit = Fruit.getRandomInstance();
            s.length++;
        }

        for (int l = 1; l < s.length; l++) {
            if (fruit.isCollision(s.snakeX[l], s.snakeY[l])) {
                fruit = Fruit.getRandomInstance();
            }
            if ((s.snakeX[0] == s.snakeX[l]) && (s.snakeY[0] == s.snakeY[l])) {
                timer.stop();
                gameOver();
            }
        }

        if (s.length == 399) fatSnake();

        repaint();
    }

    public class KeyBoard extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode();
            if (key == KeyEvent.VK_UP && s.direction != 2) s.direction = 0;
            if (key == KeyEvent.VK_DOWN && s.direction != 0) s.direction = 2;
            if (key == KeyEvent.VK_RIGHT && s.direction != 3) s.direction = 1;
            if (key == KeyEvent.VK_LEFT && s.direction != 1) s.direction = 3;
            if (key == KeyEvent.VK_ESCAPE) System.exit(0);
            if (key == KeyEvent.VK_SPACE) pause();
            if (key == KeyEvent.VK_F1) {
                pause();
                helpme();
            }
            if (key == KeyEvent.VK_F2) {
                pause();
                about();
            }
        }
    }

}

