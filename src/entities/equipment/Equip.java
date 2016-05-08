package entities.equipment;

import entities.GameObject;
import interfaces.Actionable;
import interfaces.Team;
import interfaces.Typable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public abstract class Equip extends GameObject implements Typable, Actionable, Team{

    boolean equipped;

    protected int damage;
    protected double cooldown;
    protected int timer = -1;
    protected int volley;
    protected String type;

    public String[] getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String[] itemInfo) {
        this.itemInfo = itemInfo;
    }

    protected String[] itemInfo;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    protected BufferedImage image;

    @Override
    public boolean isAlly() {
        return ally;
    }

    @Override
    public void setAlly(boolean ally) {
        this.ally = ally;
    }

    protected boolean ally;

    public int getLevel() {
        return level;
    }

    protected int level = 1;

    public void setXY(double x, double y){
        initLocation(x, y);
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public void update(){
        this.timerTick();
    }

    @Override
    public void drawImage(Graphics g){
        g.setColor(Color.yellow);
        g.drawRect(0, 0, 2, 2);
    }

    public void randomizestats(int level){
        damage = damage * level/4 + 1;
        cooldown = cooldown / (level/4 + 1);

        this.level = level;
        this.itemInfo = this.getInfoArray();
    }

    public void timerTick(){
        if(timer > cooldown){
            timer = - volley;
        }
        else if (timer >= 0){
            timer ++;
        }
    }

    protected boolean canAct(){
        if(timer < 0){
            timer ++;
            return true;
        }
        else{
            return false;
        }
    }

    public String getType(){
        return type;
    }

    protected void initInfo(String name, String[] description){
        this.name = name;
        this.itemInfo = description;
    }
    protected String[] getInfoArray(){
        return new String[]{
                "Damage: " + damage,
                "Cooldown: " + cooldown,
                "volley: " + volley
        };
    }

}
