package Work1;

import java.awt.*;
import java.util.Random;

public class BackGround {
    private float r;
    private float g;
    private float b;
    private float vR; // Скорость изменения цветов 1 ед/c
    private float vG;
    private float vB;
    private Color color;

    public Color getColor() {
        return color;
    }

    public BackGround() {
        Random rnd = new Random();
        r = 255;
        g = 255;
        b = 255;
        color = new Color((int)r, (int)g, (int)b);
        vR = -(float) rnd.nextInt(100);
        vG = -(float) rnd.nextInt(100);
        vB = -(float) rnd.nextInt(100);
        // System.out.println("vR="+vR+" vG="+vG+" vB="+vB);
    }

    public void update(float dT) {
        r += dT * vR;
        g += dT * vG;
        b += dT * vB;
        Random rnd = new Random();
        if (r < 50 || r > 255) vR = -vR; // <50 - избегаем мрачных цветов!
        if (g < 50 || g > 255) vG = -vG;
        if (b < 50 || b > 255) vB = -vB;

        color = new Color((int) r, (int) g, (int) b);
        // ??????????????????????????????????????????????????????
        // Может можно изменить цвет без создания нового объекта?
        // типа color=Color.RGB(r,g,b), но я не нашел.          ?
        // ??????????????????????????????????????????????????????
    }
}
