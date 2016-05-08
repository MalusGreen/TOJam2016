package entities.equipment;

import entities.GameObject;
import interfaces.Actionable;
import interfaces.Team;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public abstract class Equip extends GameObject implements Actionable, Team{

    boolean equipped;

    protected int damage;
    protected double cooldown;
    protected int timer = -1;
    protected int volley;

    @Override
    public boolean isAlly() {
        return ally;
    }

    @Override
    public void setAlly(boolean ally) {
        this.ally = ally;
    }

    protected boolean ally;

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

}
