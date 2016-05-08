package client.panels;

import system.Inventory;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class InventoryPane extends ItemPane {


    public InventoryPane(Inventory items, Inventory other, String btnName) {
        super(items, other, btnName);
    }

    @Override
    void transaction() {

    }
}
