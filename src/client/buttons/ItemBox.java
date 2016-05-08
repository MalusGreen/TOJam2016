package client.buttons;

import client.pages.ShipScreen;
import client.panels.HoverPanel;
import client.panels.ItemPane;
import entities.equipment.Equip;
import graphics.PrettyBtn;
import interfaces.Page;
import system.InputState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class ItemBox extends PrettyBtn implements MouseMotionListener{
    ItemPane itemPane;
    Equip e;
    HoverPanel hoverPanel;

    public ItemBox(ItemPane itemPane, Equip equip, ImageIcon hoverImage) {
        super(new ImageIcon(equip.getImage()), hoverImage);
        this.itemPane = itemPane;
        this.e = equip;
        addMouseMotionListener(this);
    }

    private void initHoverPanel(){
        hoverPanel = new HoverPanel();
        hoverPanel.setBackground(new Color(0,0,0));

        hoverPanel.addText(e.getName(), Color.cyan);
        e.getItemInfo();
    }

    public ItemBox(ItemPane itemPane, ImageIcon hoverImage) {
        super(hoverImage, hoverImage);
        this.itemPane = itemPane;
        addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e){
        InputState.currentDragged = this.e;
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if(InputState.currentDragged != null){
            InputState.mouseX = e.getXOnScreen() - 7;
            InputState.mouseY = e.getYOnScreen() - 38;
            ((ShipScreen)Page.pages.get("Ship Screen")).drop();
        }
        InputState.currentDragged = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        InputState.mouseX = e.getXOnScreen() - 7;
        InputState.mouseY = e.getYOnScreen() - 38;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g){
        if(e != null && e.isEquipped()){
            g.setColor(new Color(255,255,0,90));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        super.paintComponent(g);
    }
}
