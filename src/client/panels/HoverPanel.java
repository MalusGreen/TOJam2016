package client.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-08.
 */
public class HoverPanel extends JPanel {
    public HoverPanel(){
        super();
        GridLayout grid = new GridLayout();
        this.setLayout(grid);
        grid.setRows(5);
        grid.setColumns(1);
        this.setBackground(new Color(0,0,0,0));
    }

    public void addText(String text, Color c){
        JLabel l = new JLabel(text);
        l.setText(text);
        l.setForeground(c);
        l.setBackground(new Color(0,0,0));
        l.setVisible(true);
        this.add(l);
    }
}
