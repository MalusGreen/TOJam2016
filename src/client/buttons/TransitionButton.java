package client.buttons;

import driver.Driver;
import graphics.PrettyBtn;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Kevin Zheng on 2016-05-03.
 */
public class TransitionButton extends PrettyBtn {

    private String transition;

    public TransitionButton(String title, String transition, int type){
        super(title, type, Color.gray);
        this.transition = transition;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Driver.getMainFrame().changePage(transition);
    }
}
