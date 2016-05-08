package driver;

import interfaces.Page;
import client.pages.*;
import system.InputState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * Created by Kevin Zheng on 2016-05-02.
 */
public class DriverFrame extends JFrame implements ActionListener{
    private Timer timer;
    private CardLayout cardLayout;
    private Page currentPage;

    DriverFrame(String name){
        super(name);

        initFrame();
        initPages();
        initTimer();
    }

    private void initFrame(){
        setVisible(true);
        setSize(1000, 1000);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initPages(){
        cardLayout = new CardLayout();

        this.setLayout(cardLayout);

        JPanel mainMenu = new MainMenu();
        JPanel gameScreen = new GameScreen();
        JPanel optionsScreen = new OptionsScreen();
        JPanel shipScreen = new ShipScreen();

        addPanel("Main Menu", mainMenu);
        addPanel("Game Screen", gameScreen);
        addPanel("Options Screen", optionsScreen);
        addPanel("Ship Screen", shipScreen);

        currentPage = (Page) mainMenu;
    }

    private void addPanel(String name, JPanel panel){
        Container container = this.getContentPane();

        container.add(name, panel);
        Page.pages.put(name, (Page)panel);
    }

    private void initTimer(){
        timer = new Timer(16, this);
        timer.addActionListener(this);
        timer.start();
    }

    public void changePage(String name){
        cardLayout.show(this.getContentPane(), name);
        this.currentPage = Page.pages.get(name);
        ((JPanel)currentPage).requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == timer){
            currentPage.update();
        }
        repaint();
    }

    @Override
    public void paint(Graphics g){

        super.paint(g);
    }
}
