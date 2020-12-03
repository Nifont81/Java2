package Work1;

import java.awt.*;

public interface GraphObj {
     void update(GameCanvas canvas, float deltaTime);
    void render(GameCanvas canvas, Graphics g);
}
