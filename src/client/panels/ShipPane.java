package client.panels;

import entities.equipment.Equip;
import entities.ships.Ship;
import system.GameState;
import system.InputState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class ShipPane extends JPanel {
    public ShipPane(){
        initPane();
    }

    private void initPane(){
        this.setLayout(null);
        this.setBackground(new Color(0,0,0,0));
    }

    public void setEquipment(Equip e, int x, int y){
        GameState.getPlayer().getShip().setCompenentEquipment(e, x, y);
    }

    @Override
    public void paintComponent(Graphics g){
        Ship ship = GameState.getPlayer().getShip();
        ship.setLocation(new Point.Double(0,0));
        ship.update();
        ship.setAngle(-Math.PI/2);

        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D imageG = (Graphics2D)image.getGraphics();

        imageG.translate(this.getWidth()/2, this.getHeight()/2);

        ship.draw(imageG);

        g.drawImage(image, 0, 0, null);
    }

    public void dropIn(){
        Rectangle rect = this.getBounds();
        if(rect.contains(InputState.mouseX, InputState.mouseY)){
            double x = InputState.mouseX - rect.x - rect.getWidth()/2;
            double y = InputState.mouseY - rect.y - rect.getHeight()/2;

            Ship ship = GameState.getPlayer().getShip();
            ship.setCompenentEquipment(InputState.currentDragged, (int)x, (int)y);
        }
    }
}
