package client.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-08.
 */
public class HoverPanel extends JPanel {
    public HoverPanel(){
        super();

    }

    public void addText(String text, Color c){
        JLabel l = new JLabel(text);
        l.setForeground(c);
        l.setBackground(new Color(0,0,0,0));
        this.add(l);
    }
}
