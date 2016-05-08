package system;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class GameState{
    private static Player player;
    private static Shop shop;
    private static Arena arena;

    public static Player getPlayer(){
        if(player == null){
            player = new Player();
        }
        return player;
    }

    public static Shop getShop(){
        if(shop == null){
            shop = new Shop();
        }
        return shop;
    }

    public static Arena getArena(){
        if(arena == null){
            arena = new Arena();
        }
        return arena;
    }


    public static void update(){
        GameState.getPlayer().update();
        GameState.getArena().update();
    }

    public static void draw(Graphics g){
        Camera.getCamera().panCamera(g);
        GameState.getPlayer().draw(g);
        GameState.getArena().draw(g);
    }


    private GameState(){

    }
}
