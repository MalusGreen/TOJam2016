package system;

import entities.equipment.Equip;
import entities.equipment.fighter.FT_LaserRifle;
import entities.equipment.fighter.FT_MachineGun;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class Shop {
    Inventory inventory;

    public Shop(){
        init();
        initItems();
    }

    private void init(){
        inventory = new Inventory();
    }

    private void initItems(){
        addItemofLevel(new FT_MachineGun(), 3);
        addItemofLevel(new FT_MachineGun(), 4);
        addItemofLevel(new FT_LaserRifle(), 3);
        addItemofLevel(new FT_LaserRifle(), 4);
    }

    private void addItemofLevel(Equip e, int level){
        e.randomizestats(level);
        this.inventory.addEquip(e);
    }

    public Inventory getInventory(){
        return inventory;
    }


}
