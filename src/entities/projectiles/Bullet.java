package entities.projectiles;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class Bullet extends Projectile{

    public Bullet(int x, int y, int damage, double angle, boolean ally){
        init(x, y, damage, angle, ally);
    }




    @Override
    public void drawImage(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(0, 0, size, size);
    }

    /**
     * Bullet's travel at a constant velocity;
     */
    @Override
    public void act() {
        projectileVelocity();
    }

    @Override
    protected void init(int x, int y, int damage, double angle, boolean ally) {
        speed = 20;
        size = 2;
        super.init(x, y, damage, angle, ally);
    }
}
