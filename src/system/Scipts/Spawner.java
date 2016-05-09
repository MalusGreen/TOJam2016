package system.Scipts;

import entities.ships.Ship;
import entities.ships.destoryer.Neptune;
import entities.ships.fighter.Drone;
import interfaces.Actionable;
import system.GameState;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class Spawner implements Actionable {
    private int timer = 10000;

    private int mapSize = 10000;

    private Ship playerShip;

    private boolean ally;

    public Spawner(boolean ally){
        this.ally = ally;
    }


    private void spawnFighters(){
        for(int i = 0; i < 10; i++){
            Ship ship = new Drone((int)(Math.random()*mapSize - mapSize/2 + playerShip.getLocation().x),
                                  (int)(Math.random()*mapSize - mapSize/2 + playerShip.getLocation().y));

            new DroneAI(ship);

            ship.setAlly(ally);

            GameState.getArena().addShip(ship);
        }
    }

    private void spawnDestroyers(){
        for(int i = 0; i < 3; i++){
            Ship ship = new Neptune((int)(Math.random()*mapSize - mapSize/2 + playerShip.getLocation().x),
                                   (int)(Math.random()*mapSize - mapSize/2 + playerShip.getLocation().y));

            new NeptuneAI(ship);

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
        if(GameState.getArena().getEnemies().size() + GameState.getArena().getAllies().size() > 40){
            return;
        }
        if(timer > 10000){
            playerShip = GameState.getPlayer().getShip();
            spawnFighters();
            spawnDestroyers();
            timer = 0;
        }
    }
}
