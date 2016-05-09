package entities.ships.fighter;

import entities.equipment.fighter.FT_LaserRifle;
import entities.ships.Ship;
import entities.ships.ShipComponent;
import system.ArtHelper;

import java.awt.image.BufferedImage;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class SkySplicer extends Ship{
    ShipComponent splicerBody;

    public SkySplicer(int x, int y) {
        initLocation(x, y);
        initComponents();
        initInfo("SkySplicer", 50, "A standard issue D-Class Fighter. For its versatility in combat it remains a staple of space warfare to this day.");
    }

    @Override
    protected void initShipTypeAccel() {
        fighter();
    }

    protected void initComponents(){

        BufferedImage image = ArtHelper.getImage("Scc_Sections/Section_19.png");
        splicerBody = new ShipComponent(image, 0, 0);
        splicerBody.setPivot(image.getWidth()/3, image.getHeight()/2);
        this.addEquipSlot(0, image.getHeight()/4);
        this.addEquipSlot(0, -image.getHeight()/4);
        this.equipslots.get(0).setEquip(new FT_LaserRifle());
        this.equipslots.get(1).setEquip(new FT_LaserRifle());

        components.add(splicerBody);
    }
}
