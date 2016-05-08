package entities.projectiles;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class Laser extends Projectile{
    public Laser(int x, int y, int damage, double angle, boolean ally) {
        init(x, y, damage, angle, ally);
    }

    @Override
    public void act() {
        projectileVelocity();
    }

    @Override
    public void drawImage(Graphics g) {
        g.setColor(Color.cyan);

        double realAngle = angle + Math.PI;

        g.drawLine(0, 0, (int)(size * Math.cos(realAngle)), (int) (size * Math.sin(realAngle)));
    }

    @Override
    protected void init(int x, int y, int damage, double angle, boolean ally) {
        speed = 30;
        size = 5;
        super.init(x, y, damage, angle, ally);
    }
}
