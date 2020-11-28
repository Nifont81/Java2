package Work1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private int countBall = 5; // количество шариков;
    private int maxBall = 20; // максимальное количество шариков;

    Sprite[] sprites = new Sprite[maxBall];

    public static void main(String[] args) {
        new MainCircles();
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // Располагаем окно ровно по центру экрана;

        setTitle("Circles");
        GameCanvas canvas = new GameCanvas(this);
        initApplication();
        add(canvas);
        setVisible(true);
    }

    private void initApplication() {
        for (int i = 0; i < countBall; i++) {
            sprites[i] = new Ball();
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    public void onMouse(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && countBall < maxBall) {
            sprites[countBall++] = new Ball();
            System.out.println("Шарик добавлен!");
        } else if (e.getButton() == MouseEvent.BUTTON3 && countBall > 0) {
            countBall--;
            System.out.println("Шарик удален!");
        }
    }

    private void update(GameCanvas canvas, float deltaTime) {
        canvas.backGround.update(deltaTime);
        for (int i = 0; i < countBall; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        canvas.setBackground(canvas.backGround.getColor());
        for (int i = 0; i < countBall; i++) {
            sprites[i].render(canvas, g);
        }
    }
}