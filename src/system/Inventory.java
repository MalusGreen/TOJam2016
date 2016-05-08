package system;

import entities.equipment.Equip;

import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-05-03.
 */
public class Inventory {

    ArrayList<Equip> items;
    public Inventory(){
        init();
    }

    private void init(){
        items = new ArrayList<Equip>();
    }

    public void addEquip(Equip item){
        items.add(item);
    }

    public void removeEquip(Equip item){
        items.remove(item);
    }

    public ArrayList<Equip> getListofType(String type){
        if(type.equals("All")){
            return items;
        }

        ArrayList<Equip> newList = new ArrayList<Equip>();

        for(Equip item: items){
            if(item.getType().equals(type))
                newList.add(item);
        }

        return newList;
    }
}
