package system.Scipts;

import entities.ships.Ship;
import interfaces.AI;
import system.GameState;
import system.MathHelper;

import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public abstract class ShipAI implements AI {
    private static ArrayList<AI> ais = new ArrayList<>();

    public static void shipDecisions(){
        ais.forEach(ai -> {
            ai.decision();
            ai.action();
        });
    }


    protected Ship ship;
    public ShipAI(Ship ship){
        this.ship = ship;
        ship.setLevel(MathHelper.generateLevel(GameState.getPlayer().getLevel()));
        ais.add(this);
    }
}
