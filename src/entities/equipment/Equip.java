package entities.equipment;

import entities.GameObject;
import entities.ships.Ship;
import interfaces.Actionable;
import interfaces.Team;
import interfaces.Typable;
import system.MathHelper;

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

    public Ship getTarget() {
        return target;
    }

    public void setTarget(Ship target) {
        this.target = target;
    }

    protected Ship target;

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
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(angle);
        g.drawRect(-3, -3, 6, 6);
        g2d.rotate(-angle);
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

    protected double getTargetAngle(){
        double angle;
        if(target != null){
            if(!target.isAlive()){
                target = null;
                return this.getTargetAngle();
            }
            angle = MathHelper.getAngle(this.location, target.getLocation());
        }
        else{
            this.target = MathHelper.getTarget(ally, this.location, 500);
            angle = this.angle;
        }
        return angle;
    }

}
