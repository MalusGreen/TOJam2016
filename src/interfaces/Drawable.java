package interfaces;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public interface Drawable {
    void draw(Graphics g);
    void drawImage(Graphics g);
    void setAngle(double angle);
}
