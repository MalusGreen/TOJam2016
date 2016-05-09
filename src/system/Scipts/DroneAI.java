package system.Scipts;

import entities.ships.Ship;
import system.GameState;
import system.MathHelper;

import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class DroneAI extends ShipAI{
    public DroneAI(Ship ship){
        super(ship);
    }

    @Override
    public void decision() {
        ArrayList<Ship> list;

        if(ship.isAlly()){
            list = GameState.getArena().getEnemies();
        }
        else{
            list = GameState.getArena().getAllies();
        }

        if(ship.getTarget() == null){
            for(Ship s: list){
                if(MathHelper.inRange(ship.getLocation(), s.getLocation(), 500)){
                    ship.setTarget(s);
                    break;
                }
            }
        }
        else if(!MathHelper.inRange(ship.getLocation(), ship.getTarget().getLocation(), 500)){
            ship.setTarget(null);
        }
        else if(!ship.getTarget().isAlive()){
            ship.setTarget(null);
        }
    }

    @Override
    public void action() {
        if(ship.getTarget() == null){
            return;
        }
        ship.setAngle(MathHelper.getAngle(ship.getLocation(), ship.getTarget().getLocation()));
        ship.act();
    }
}
