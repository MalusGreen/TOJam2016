package system;

import entities.ships.Ship;
import entities.ships.fighter.SkySplicer;
import interfaces.Updatable;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class Player implements Updatable{
    Ship ship;

    int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    int cash;

    public Player(){
        cash = 0;
        level = 1;
        this.setShip(new SkySplicer(100, 100));
        GameState.getArena().addShip(ship);
    }

    public void setShip(Ship ship){
        this.ship = ship;
        ship.setAlly(true);
    }


    public Ship getShip(){
        return ship;
    }

    @Override
    public void update() {
        if (InputState.SPACE)
            ship.act();
        if (InputState.W)
            ship.accel();
        if (InputState.D)
            ship.turn(true);
        if (InputState.A)
            ship.turn(false);

        ship.update();

        Camera.getCamera().setTarget(ship.getLocation());

    }

    public void draw(Graphics g){
        ship.draw(g);
    }

}
