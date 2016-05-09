package client.pages;

import client.buttons.TransitionButton;
import interfaces.Page;
import system.ArtHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Kevin Zheng on 2016-05-02.
 */
public class OptionsScreen extends JPanel implements Page {
    public OptionsScreen(){

        this.setLayout(null);

        this.setBackground(new Color(79, 107, 129));

        TransitionButton backButton = new TransitionButton("<", "Main Menu", 2);
        backButton.setBounds(50, 10, 50, 50);
        this.add(backButton);
    }


    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        BufferedImage image = ArtHelper.getImage("goat.jpg");

        g.drawImage(image, 500 - image.getWidth()/2 ,500 - image.getHeight()/2 , null);
    }

    @Override
    public void toPage() {

    }
}
