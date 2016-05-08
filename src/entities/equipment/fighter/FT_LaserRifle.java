package entities.equipment.fighter;

import entities.equipment.Equip;
import entities.projectiles.Laser;
import system.GameState;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class FT_LaserRifle extends Equip{

    public FT_LaserRifle() {
        damage = 2;
        volley = 12;
        cooldown = 60;
    }

    @Override
    public void act() {
        if (!canAct()) {
            return;
        }
        GameState.getArena().addProjectile(new Laser((int)location.x, (int)location.y, damage, this.angle, ally));
    }
}