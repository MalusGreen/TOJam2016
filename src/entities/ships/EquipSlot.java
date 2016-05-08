package entities.ships;

import entities.equipment.EmptyEquip;
import entities.equipment.Equip;
import interfaces.Actionable;
import interfaces.Drawable;
import interfaces.Updatable;
import system.MathHelper;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class EquipSlot implements Actionable, Updatable, Drawable{
    double x;
    double y;

    double relative_x;
    double relative_y;

    double angle;

    boolean ally;

    public Equip getEquip() {
        return equip;
    }

    Equip equip;

    public EquipSlot(int x, int y, boolean ally){
        this.relative_x = x;
        this.relative_y = y;
        this.ally = ally;

        equip = new EmptyEquip();
    }

    public void setAlly(boolean ally){
        this.ally = ally;
        this.equip.setAlly(ally);
    }

    public void setXY(double x, double y){
        this.x = x;
        this.y = y;

        this.equip.setXY(this.getX(), this.getY());
        this.equip.setAlly(ally);
    }

    public void setRelativeXY(double x, double y){
        relative_x = x;
        relative_y = y;

        this.equip.setXY(this.getX(), this.getY());
    }

    public void setEquip(Equip e){
        this.equip = e;
        this.equip.setXY(this.getX(), this.getY());
    }

    public double getX(){
        return relative_x + x;
    }

    public double getY(){
        return relative_y + y;
    }


    public void act(){
        equip.act();
    }

    public Rectangle getRect(){
        return new Rectangle((int)(x - 12), (int)(y - 12), 24, 24);
    }

    @Override
    public void draw(Graphics g) {
        drawImage(g);
    }

    @Override
    public void drawImage(Graphics g) {
        equip.draw(g);
    }

    @Override
    public void setAngle(double angle) {
        double oldAngle = this.angle;
        this.angle = angle;
        this.equip.setAngle(angle);

        Point.Double newPoint = MathHelper.rotate(angle, oldAngle, new Point.Double(relative_x, relative_y));

        this.relative_x = newPoint.x;
        this.relative_y = newPoint.y;

        this.equip.setXY(this.getX(), this.getY());
    }

    @Override
    public void update() {
        this.equip.update();
    }
}
