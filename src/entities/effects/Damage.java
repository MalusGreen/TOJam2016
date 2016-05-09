package entities.effects;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class Damage extends Effect{
    int damage;
    public Damage(int x, int y, int damage) {
        initLocation(x, y);
        health = 360;
        MAXHEALTH = 360;
        this.damage = damage;
    }

    @Override
    public void act() {

    }

    @Override
    public void drawImage(Graphics g) {
        g.drawString(String.valueOf(damage), (int)(Math.random() * 2) , (int)(Math.random() * 2 + (360 - health)/10));
    }
}
