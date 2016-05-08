package client.pages;

import interfaces.Page;
import client.panels.InventoryPane;
import client.panels.ItemPane;
import client.panels.ShipPane;
import client.panels.ShopPane;
import graphics.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin Zheng on 2016-05-03.
 */
public class ShipScreen extends JPanel implements Page, ActionListener{

    private ItemPane shopPane;
    private ItemPane inventoryPane;
    private ShipPane shipPane;
    private Grid grid;

    public ShipScreen(){
        initPage();
        initGrid();
    }

    private void initPage(){
        setLayout(null);

        shopPane = new ShopPane();
        shipPane = new ShipPane();
        inventoryPane = new InventoryPane();

        shopPane.setBounds(50, 50, 400, 900);
        inventoryPane.setBounds(550, 50, 400, 900);

        this.add(shopPane);
        this.add(inventoryPane);
    }

    private void initGrid(){
        grid = new Grid(Color.darkGray, 10, 10, 100);
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.black);
        grid.draw(g, 0, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
