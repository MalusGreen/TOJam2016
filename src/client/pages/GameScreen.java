package client.pages;

import client.panels.MiniMap;
import interfaces.Page;
import system.GameState;
import system.InputState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Kevin Zheng on 2016-05-02.
 */
public class GameScreen extends JPanel implements Page, MouseListener, MouseMotionListener, KeyListener {

    public GameScreen(){
        init();
    }

    private void init(){
        initListeners();
        initMiniMap();
    }

    private void initListeners(){
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    private void initMiniMap(){
        this.setLayout(null);

        JPanel minimap = new MiniMap();
        minimap.setBounds(800, 800, 128, 128);
        this.add(minimap);
    }

    @Override
    public void update() {
        GameState.update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setHints(g);

        GameState.draw(g);
        this.setBackground(Color.black);
    }


    private void setHints(Graphics g){
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(rh);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            InputState.W = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            InputState.D = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            InputState.A = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            InputState.SPACE = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            InputState.W = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            InputState.D = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            InputState.A = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            InputState.SPACE = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void toPage() {
        GameState.getArena().reset();
    }
}
