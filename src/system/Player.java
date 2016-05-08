package system;

import entities.equipment.Equip;
import entities.equipment.fighter.FT_MachineGun;
import entities.ships.Ship;
import entities.ships.frigate.MissileFrigate;
import interfaces.Updatable;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class Player implements Updatable{
    Ship ship;

    Inventory inventory;

    int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    int cash;

    public Player(){
        initPlayer();
        initItems();
    }

    private void initPlayer(){
        cash = 0;
        level = 1;
        inventory = new Inventory();
        this.setShip(new MissileFrigate(100, 100));
        this.ship.setPlayer(true);
        GameState.getArena().addShip(ship);
    }

    private void initItems(){
        addItemofLevel(new FT_MachineGun(), 2);
        addItemofLevel(new FT_MachineGun(), 2);
    }

    private void addItemofLevel(Equip e, int level){
        e.randomizestats(level);
        this.inventory.addEquip(e);
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



    public Inventory getInventory(){
        return inventory;
    }

}
