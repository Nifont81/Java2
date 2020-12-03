package Work1;

import java.awt.*;
import java.util.Random;

public class Box extends Sprite implements GraphObj{
    Random rnd = new Random();

    private final Color color;
    private float vX;
    private float vY;
    private float vKx;

    Box() {
        halfHeight = 20 + (float) (Math.random() * 50f);
        halfWidth = halfHeight;
        color = new Color(rnd.nextInt());
        vX = (float) (50f + (Math.random() * 250f)); // Немного увеличил разброс,
        vY = (float) (50f + (Math.random() * 250f)); // а то все почти одинаково двигаются;
    }

    @Override
    public void update(GameCanvas canvas, float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;

        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY;
        }
    }

    @Override
    public void render(GameCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillRect((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
}