package system.Scipts;

import entities.ships.Ship;
import system.GameState;
import system.MathHelper;

/**
 * Created by Kevin Zheng on 2016-05-08.
 */
public class NeptuneAI extends ShipAI{
    private Ship target;
    private boolean coasting;

    public NeptuneAI(Ship ship) {
        super(ship);
        coasting = true;
    }

    @Override
    public void decision() {
        for(Ship ship: GameState.getArena().getTargets(this.ship.isAlly())){
            if(target == null){
                target = ship;
            }
            else if(ship.getType().equals("Frigate")){
                if(ship.getLocation().distance(this.ship.getLocation()) < target.getLocation().distance(this.ship.getLocation())){
                    target = ship;
                }
            }
        }
    }

    @Override
    public void action() {
        if(target != null){
            double tg_angle = MathHelper.getAngle(ship.getLocation(), target.getLocation());
            double dif = tg_angle - ship.getAngle();
            if(coasting){
                if(dif > 0.1){
                    ship.turn(true);
                }
                else if(dif < 0.1){
                    ship.turn(false);
                }
                else{
                    coasting = true;
                }
            }
            else{
                if(Math.abs(dif) > Math.PI/4){
                    coasting = false;
                }
            }
            if(MathHelper.inRange(ship.getLocation(), target.getLocation(), 500)){
                ship.act();
            }
        }
        ship.accel();
    }
}
