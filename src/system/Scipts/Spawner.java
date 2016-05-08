package system.Scipts;

import entities.ships.Ship;
import entities.ships.fighter.Drone;
import interfaces.Actionable;
import system.GameState;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class Spawner implements Actionable {
    private int timer = 10000;

    private Ship playerShip;

    private boolean ally;

    public Spawner(boolean ally){
        this.ally = ally;
    }


    private void spawnFighters(){
        for(int i = 0; i < 2; i++){
            Ship ship = new Drone((int)(Math.random()*1000 - 500 + playerShip.getLocation().x),
                                  (int)(Math.random()*1000 - 500 + playerShip.getLocation().y));

            new DroneAI(ship);

            ship.setAlly(ally);

            GameState.getArena().addShip(ship);
        }
    }

    private void timerTick(){
        timer++;
    }

    @Override
    public void act() {
        timerTick();
        if(timer > 10000){
            playerShip = GameState.getPlayer().getShip();
            spawnFighters();

            timer = 0;
        }
    }
}
