package entities.ships.frigate;

import entities.equipment.fighter.FT_MachineGun;
import entities.ships.Ship;
import entities.ships.ShipComponent;
import system.ArtHelper;

import java.awt.image.BufferedImage;

/**
 * Created by Kevin Zheng on 2016-05-08.
 */
public class MissileFrigate extends Ship{

    public MissileFrigate(int x, int y){
        initLocation(x, y);
        initComponents();
        initShipTypeAccel();
        initInfo("MissileFrigate", 100, "A staple of war-fighting, the usages of frigate type ships have been documented to the Age of Exploration.");
    }

    @Override
    protected void initShipTypeAccel() {
        frigate();
    }

    private ShipComponent frigateBody;
    @Override
    protected void initComponents() {
        BufferedImage image = ArtHelper.getImage("Scc_Sections/Section_21.png");
        frigateBody = makeComponent(image, image.getWidth()/2 - 10, image.getHeight()/2);

        this.addComponentSlot(image.getWidth()/2,  3 * image.getHeight()/8);
        this.setEquipment(new FT_MachineGun());

        this.addComponentSlot(image.getWidth()/2, -3 * image.getHeight()/8);
        this.setEquipment(new FT_MachineGun());

        components.add(frigateBody);
    }
}
