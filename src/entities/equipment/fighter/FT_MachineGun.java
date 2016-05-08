package entities.equipment.fighter;

import entities.equipment.Equip;
import entities.projectiles.Bullet;
import system.GameState;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class FT_MachineGun extends Equip {

    public FT_MachineGun(){
        damage = 1;
        volley = 6;
        cooldown = 60;
    }

    @Override
    public void act() {
        if (!canAct()) {
            return;
        }
        GameState.getArena().addProjectile(new Bullet((int)location.x, (int)location.y, damage, this.angle, ally));
    }
}
