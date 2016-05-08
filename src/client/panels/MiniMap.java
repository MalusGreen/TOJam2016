package client.panels;

import entities.ships.Ship;
import system.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class MiniMap extends JPanel{
    @Override
    public void paintComponent(Graphics g){
    }

    private BufferedImage drawMiniMap(){
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics g = image.getGraphics();

        ArrayList<Ship> allies = GameState.getArena().getAllies();
        ArrayList<Ship> enemies = GameState.getArena().getEnemies();


        g.setColor(Color.blue);
        drawShips(allies, g);
        g.setColor(Color.red);
        drawShips(enemies, g);

        return image;
    }

    private void drawShips(ArrayList<Ship> list, Graphics g){
        Ship playerShip = GameState.getPlayer().getShip();
        for(Ship ship: list){

            Point.Double location = ship.getLocation();

            playerShip.getLocation();

            g.drawRect();
        }
    }
}
