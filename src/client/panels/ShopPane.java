package client.panels;

import system.Inventory;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class ShopPane extends ItemPane {

    public ShopPane(Inventory items, Inventory other, String btnName) {
        super(items, other, btnName);
    }

    @Override
    void transaction() {

    }
}
