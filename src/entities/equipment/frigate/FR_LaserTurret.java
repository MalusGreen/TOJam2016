package entities.equipment.frigate;

import entities.equipment.Equip;
import entities.projectiles.Laser;
import system.ArtHelper;
import system.GameState;

/**
 * Created by Kevin Zheng on 2016-05-08.
 */
public class FR_LaserTurret extends Equip {
    public FR_LaserTurret() {
        damage = 5;
        volley = 4;
        cooldown = 20;
        type = "Frigate";
        this.setImage(ArtHelper.getImage("Scc_Bland_Sections/Scc_Weapon_3.png"));
        initInfo("FR_MachineTurret", this.getInfoArray());
    }

    @Override
    public void act() {
        if (!canAct()) {
            return;
        }

        double angle = this.getTargetAngle();

        GameState.getArena().addProjectile(new Laser((int)location.x, (int)location.y, damage, angle, ally));
    }
}
