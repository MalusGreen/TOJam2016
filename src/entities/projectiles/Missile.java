package entities.projectiles;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class Missile extends Projectile{
    public Missile(int x, int y, int damage, double angle, boolean ally) {
        init(x, y, damage, angle, ally);
    }

    @Override
    public void act() {

    }

    @Override
    public void drawImage(Graphics g) {
        g.setColor(Color.orange);
        g.drawRect(0, 0, size, size);
    }

    @Override
    protected void init(int x, int y, int damage, double angle, boolean ally) {
        super.init(x, y, damage, angle, ally);
    }
}
