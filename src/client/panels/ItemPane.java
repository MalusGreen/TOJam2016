package client.panels;

import client.buttons.ItemBox;
import entities.equipment.Equip;
import graphics.PrettyBtn;
import system.ArtHelper;
import system.InputState;
import system.Inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public abstract class ItemPane extends JPanel implements ActionListener{
    private String typeShown;
    private JButton transaction;
    private JPanel itemPanel;
    private Inventory items;
    private Inventory other;

    private String[] paneTitles = new String[]{
            "Fighter",
            "Frigate",
            "Corvette",
            "Destroyer",
            "Cruiser",
            "Capital"
    };

    public ItemPane(Inventory items, Inventory other, String btnName){
        init(items, other);
        initVar();
        initPane();
        initItemPanel();
        initButtons(btnName);
    }

    public void init(Inventory items, Inventory other) {
        this.items = items;
        this.other = other;
    }

    private void initVar(){
        typeShown = "All";
        this.setLayout(null);
    }

    private void initPane(){
        this.setBackground(new Color(0,0,0,0));
    }

    private void initButtons(String btnName){
        initSortButtons();
        initTransactionButton(btnName);
    }

    private void initTransactionButton(String btnName){
        transaction = new PrettyBtn(btnName, 1, Color.lightGray);
        transaction.setBounds(100,850,152,25);
        transaction.addActionListener(this);
        this.add(transaction);
        this.setComponentZOrder(transaction, 0);
    }

    private void initSortButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0,0,0,0));
        buttonPanel.setLayout(new GridBagLayout());
        this.add(buttonPanel);

        buttonPanel.setBounds(22, 35, 356, 50);
        for(int i = 0; i < paneTitles.length; i++){
            addTypeButton(buttonPanel, paneTitles[i], i);
        }
    }

    private void addTypeButton(JPanel buttonPanel, String title, int i){
        GridBagConstraints c = new GridBagConstraints();
        PrettyBtn button = new PrettyBtn(title, 2, Color.darkGray);
        button.addActionListener(this);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 0.8;

        if(i < 3){
            c.gridx = i;
            c.gridy = 0;
        }
        else{
            c.gridx = i - 3;
            c.gridy = 1;
        }

        buttonPanel.add(button, c);
    }

    private void initItemPanel(){
        itemPanel = new JPanel();
        itemPanel.setBackground(new Color(0,0,0,0));
        itemPanel.setLayout(null);
        itemPanel.setBounds(0, 100, 400, 720);

        this.setItems();
        this.add(itemPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() instanceof JButton){
            JButton b = (JButton) e.getSource();
            if(b.getText().equals("BUY")||b.getText().equals("SELL")){

            }
            else if(!typeShown.equals(b.getText())){
                typeShown = b.getText();
                this.setItems();
            }
        }
    }

    public void setItems(){
        ArrayList<Equip> items = this.items.getListofType(typeShown);
        itemPanel.removeAll();
        ItemBox button;
        for(int i = 0 ; i < 16*10; i++){
            ImageIcon hoverImage = new ImageIcon(ArtHelper.getEmptyImage(40,40));

            if(i < items.size()){
                button = new ItemBox(this, items.get(i), hoverImage);
            }
            else{
                button = new ItemBox(this, hoverImage);
            }
            int x = (i%10)*40;
            int y = (i/10)*40;
            button.setBounds(x, y, 40, 40);
            itemPanel.add(button);
        }
    }

    public void dropIn(){
        if(this.getBounds().contains(InputState.mouseX, InputState.mouseY)){

            if(items.getListofType("All").contains(InputState.currentDragged)){
                return;
            }
            Equip e = InputState.currentDragged;
            other.removeEquip(e);
            items.addEquip(e);
        }
    }

    abstract void transaction();
}
