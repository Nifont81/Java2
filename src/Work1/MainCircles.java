package Work1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private int countGrObj = 5; // количество объектов;
    private int maxGrObj = 20; // максимальное количество объетов;

    GraphObj[] graphObjs = new GraphObj[maxGrObj +1];

    public static void main(String[] args) {
        new MainCircles();
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // Располагаем окно ровно по центру экрана;

        setTitle("Графические объекты");
        GameCanvas canvas = new GameCanvas(this);
        initApplication();
        add(canvas);
        setVisible(true);
    }

    private void initApplication() {
        graphObjs[0]=new BackGround();
        for (int i = 1; i <= countGrObj; i++) {
            if (i % 2 == 0) graphObjs[i] = new Ball();
            else graphObjs[i] = new Box();
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    public void onMouse(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && countGrObj < maxGrObj) {
            ++countGrObj;
            if (countGrObj % 2 == 0) graphObjs[countGrObj] = new Ball();
            else graphObjs[countGrObj] = new Box();
            System.out.println("Объект добавлен!");
        } else if (e.getButton() == MouseEvent.BUTTON3 && countGrObj > 0) {
            countGrObj--;
            System.out.println("Объект удален!");
        }
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i <= countGrObj; i++) {
            graphObjs[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i <= countGrObj; i++) {
            graphObjs[i].render(canvas, g);
        }
    }
}