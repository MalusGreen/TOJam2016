package entities.ships.destoryer;

import entities.equipment.frigate.FR_LaserTurret;
import entities.ships.Ship;
import entities.ships.ShipComponent;
import system.ArtHelper;

import java.awt.image.BufferedImage;

/**
 * Created by Kevin Zheng on 2016-05-08.
 */
public class Neptune extends Ship{

    public Neptune(int x, int y){
        initLocation(x, y);
        initComponents();
        initInfo("Neptune", 1000, "All who board ships must fear the wrath of Neptune.");
    }

    @Override
    protected void initShipTypeAccel() {
        destroyer();
    }

    private ShipComponent body;

    @Override
    protected void initComponents() {
        BufferedImage image = ArtHelper.getImage("NEPTUNECHASSIS.png");
        body = makeComponent(image, image.getWidth()/3, image.getHeight()/2);

        this.addEquipSlot(20,   image.getHeight()/2 - 24);
        this.setEquipment(new FR_LaserTurret());
        this.addEquipSlot(20, - image.getHeight()/2 + 22);
        this.setEquipment(new FR_LaserTurret());

        this.addEquipSlot(10,   image.getHeight()/2 - 34);
        this.setEquipment(new FR_LaserTurret());
        this.addEquipSlot(10, - image.getHeight()/2 + 32);
        this.setEquipment(new FR_LaserTurret());

        this.addEquipSlot(50, 10);
        this.setEquipment(new FR_LaserTurret());
        this.addEquipSlot(50, -12);
        this.setEquipment(new FR_LaserTurret());

        components.add(body);
    }
}
