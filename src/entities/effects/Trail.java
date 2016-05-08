package entities.effects;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class Trail extends Effect{
    public Trail(int x, int y){
        initLocation(x, y);
        health = 120;
    }

    @Override
    public void drawImage(Graphics g) {
        g.drawRect((int)(location.x - health/2), (int) (location.y - health/2), health, health);
    }

    @Override
    public void act() {

    }
}
