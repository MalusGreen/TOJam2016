package client.panels;

import graphics.PrettyBtn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public abstract class ItemPane extends JPanel implements ActionListener{
    private String typeShown;
    private JButton transaction;

    private String[] paneTitles = new String[]{
            "Fighter",
            "Frigate",
            "Corvette",
            "Destroyer",
            "Cruiser",
            "Capital"
    };

    public ItemPane(){
        init();
    }

    public void init(){
        initVar();
        initPane();
        initButtons();
    }

    private void initVar(){
        typeShown = "All";
        this.setLayout(null);
    }

    private void initPane(){
        this.setBackground(new Color(0,0,0,0));
    }

    private void initButtons(){
        initSortButtons();
        initTransactionButton();
    }

    private void initTransactionButton(){
        transaction = new PrettyBtn("Transaction", 1, Color.lightGray);
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

    @Override
    public void actionPerformed(ActionEvent e){
    }

    abstract void transaction();
}
