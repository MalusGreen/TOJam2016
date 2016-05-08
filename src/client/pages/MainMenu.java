package client.pages;

import interfaces.Page;
import client.buttons.TransitionButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin Zheng on 2016-05-02.
 */
public class MainMenu extends JPanel implements Page, ActionListener{

    private TransitionButton[] buttons;
    private GridBagLayout layout;

    public MainMenu(){
        initPage();
        initButtons();
    }

    private void initPage(){
        layout = new GridBagLayout();
        setLayout(layout);
    }

    private void initButtons(){
        buttons = new TransitionButton[3];

        String[] text = new String[]{
                "Start",
                "Ship",
                "Options"
        };

        String[] transitions = new String[]{
                "Game Screen",
                "Ship Screen",
                "Options Screen"
        };

        for(int i = 0; i < buttons.length; i++){
            buttons[i] = new TransitionButton(text[i], transitions[i], 2);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
