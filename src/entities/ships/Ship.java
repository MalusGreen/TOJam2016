package entities.ships;

import driver.Driver;
import entities.GameObject;
import entities.equipment.Equip;
import interfaces.Actionable;
import interfaces.Collidable;
import interfaces.Team;
import system.MathHelper;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public abstract class Ship extends GameObject implements Collidable, Team, Actionable{
    protected String type;

    public Ship getTarget() {
        return target;
    }

    public void setTarget(Ship target) {
        this.target = target;
    }

    protected Ship target;

    protected double mass;

    protected double turnrate;

    protected boolean ally;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        equipslots.forEach(c -> c.getEquip().randomizestats(level));
    }

    protected int level;

    protected ArrayList<ShipComponent> components;

    protected ArrayList<EquipSlot> equipslots;

    public Ship(){
        components = new ArrayList<>();
        equipslots = new ArrayList<>();
    }

    public String getType(){
        return type;
    }

    protected abstract void initShipTypeAccel();

    public void accel(){
        if(mass == 0){
            throw new IllegalStateException();
        }
        Point.Double newPoint = MathHelper.getVector(angle , 1.0/(mass + 5));
        velocity.x += newPoint.x;
        velocity.y += newPoint.y;
    }

    public void turn(boolean increase){
        if(increase){
            this.setAngle(angle + turnrate);
        }
        else{
            this.setAngle(angle - turnrate);
        }
    }

    @Override
    public void setAngle(double angle){
        this.angle = angle;
        components.forEach(component -> component.setAngle(angle));
        equipslots.forEach(component -> component.setAngle(angle));
    }


    protected abstract void initComponents();

    public void update(){
        this.move();
        components.forEach(component -> component.setXY(location.x, location.y));
        equipslots.forEach(component -> {
            component.update();
            component.setXY(location.x, location.y);
        });

        this.checkAlive();
    }

    private void checkAlive(){
        if(health <= 0){
            this.setAlive(false);
        }
    }

    public void draw(Graphics g){
        super.draw(g);

        if(Driver.DEBUG){
            g.setColor(Color.yellow);
            for (ShipComponent component : components) {
                ((Graphics2D)g).draw(component.getEllipse());
            }
            for(EquipSlot equip: equipslots){
                equip.drawImage(g);
            }
        }
    }


    public boolean isAlly(){
        return ally;
    }

    public void setAlly(boolean ally){
        this.ally = ally;

        equipslots.forEach(c -> c.setAlly(ally));
    }

    public void act(){
        equipslots.forEach(EquipSlot::act);
    }

    public void addComponentSlot(int x, int y){
        EquipSlot e = new EquipSlot(x, y, ally);
        e.setXY(this.location.x, this.location.y);
        equipslots.add(e);
    }

    public void setCompenentEquipment(Equip e, int x, int y){
        for(EquipSlot equipslot: equipslots){
            if(equipslot.getRect().contains(x, y)){
                equipslot.setEquip(e);
            }
        }
    }

    public boolean collision(Point.Double point){
        for(ShipComponent component: components){
            if(component.getEllipse().contains(point)){
                return true;
            }
        }
        return false;
    }

    protected void fighter(){
        type = "Fighter";
        mass = 2;
        turnrate = 0.02;
    }

    protected void frigate(){
        type = "Frigate";
        mass = 4;
        turnrate = 0.01;
    }

    @Override
    public void drawImage(Graphics g){
        components.forEach(c -> c.draw(g));
    }
}
