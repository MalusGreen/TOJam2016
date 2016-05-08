package client.pages;

import graphics.PrettyBtn;
import interfaces.Page;
import client.panels.InventoryPane;
import client.panels.ItemPane;
import client.panels.ShipPane;
import client.panels.ShopPane;
import graphics.Grid;
import system.GameState;
import system.InputState;

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
    private JButton shopButton;
    private Grid grid;

    public ShipScreen(){
        initPage();
        initGrid();
        initShopButton();
    }

    private void initPage(){
        setLayout(null);

        shopPane = new ShopPane(GameState.getShop().getInventory(), GameState.getPlayer().getInventory(), "BUY");
        shipPane = new ShipPane();
        inventoryPane = new InventoryPane(GameState.getPlayer().getInventory(), GameState.getShop().getInventory(), "SELL");

        shopPane.setBounds(50, 50, 400, 900);
        shipPane.setBounds(50, 50, 400, 900);
        inventoryPane.setBounds(550, 50, 400, 900);

        this.add(shopPane);
        this.add(inventoryPane);
    }

    private void initGrid(){
        grid = new Grid(Color.darkGray, 10, 10, 100);
    }

    private void initShopButton(){
        shopButton = new PrettyBtn("$", 1, Color.lightGray);
        shopButton.setBounds(900, 10, 50, 50);

        shopButton.addActionListener(this);

        this.add(shopButton);
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.black);
        grid.draw(g, 0, 0);

        if(InputState.currentDragged !=null){
            g.drawImage(InputState.currentDragged.getImage(), InputState.mouseX, InputState.mouseY, null);
        }
    }

    private boolean isShop = true;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == shopButton) {
            if (isShop) {
                this.remove(shopPane);
                this.add(shipPane);
                isShop = false;
            } else {
                this.remove(shipPane);
                this.add(shopPane);
                isShop = true;
            }
        }
    }

    public void drop(){
        if(!isShop){
            shipPane.dropIn();
        }
        else{
            shopPane.dropIn();
            inventoryPane.dropIn();
        }

        shopPane.setItems();
        inventoryPane.setItems();
    }
}
