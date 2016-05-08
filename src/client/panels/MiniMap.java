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
    int size = 64;

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(drawMiniMap(), 0, 0, null);
    }

    private BufferedImage drawMiniMap(){
        BufferedImage image = new BufferedImage(size*2, size*2, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics g = image.getGraphics();

        ArrayList<Ship> allies = GameState.getArena().getAllies();
        ArrayList<Ship> enemies = GameState.getArena().getEnemies();

        g.setColor(new Color(255,255,255,15));
        g.drawOval(0, 0, size*2, size*2);
        g.fillOval(0, 0, size*2, size*2);

        g.setColor(Color.blue);
        drawShips(allies, g);
        g.setColor(Color.red);
        drawShips(enemies, g);

        return image;
    }

    private void drawShips(ArrayList<Ship> list, Graphics g){
        Ship playerShip = GameState.getPlayer().getShip();

        double scale = size*2/10000.0;

        for(Ship ship: list){

            Point.Double location = ship.getLocation();

            double x = location.x - playerShip.getLocation().x;
            double y = location.y - playerShip.getLocation().y;

            int rx = (int)(x * scale) + size;
            int ry = (int)(y * scale) + size;

            g.drawRect(rx, ry, 1, 1);
        }
    }
}
