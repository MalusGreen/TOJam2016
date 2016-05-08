package entities.ships.fighter;

import entities.equipment.fighter.FT_MachineGun;
import entities.ships.Ship;
import entities.ships.ShipComponent;
import system.ArtHelper;

import java.awt.image.BufferedImage;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class Drone extends Ship {
    public Drone(int x, int y){
        initLocation(x, y);
        initInfo("Drone", 50, "An unmanned scout. Easy pickings for the likes of you.");
        initComponents();
    }

    @Override
    protected void initShipTypeAccel() {
        fighter();
    }

    private ShipComponent droneBody;

    @Override
    protected void initComponents() {
        BufferedImage image = ArtHelper.getImage("Scc_Sections/Core_6.png");
        droneBody = new ShipComponent(image, 0, 0);
        droneBody.setPivot(image.getWidth()/2, image.getHeight()/2);
        addComponentSlot(0 , 0);
        this.equipslots.get(0).setEquip(new FT_MachineGun());

        components.add(droneBody);
    }
}
