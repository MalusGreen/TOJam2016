package entities.ships;

import driver.Driver;
import entities.GameObject;
import entities.equipment.Equip;
import interfaces.Actionable;
import interfaces.Collidable;
import interfaces.Team;
import interfaces.Typable;
import system.GameState;
import system.MathHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public abstract class Ship extends GameObject implements Typable, Collidable, Team, Actionable{

    protected String type;

    public Ship getTarget() {
        return target;
    }

    public void setTarget(Ship target) {
        this.target = target;

        equipslots.forEach(e -> e.getEquip().setTarget(target));
    }

    public ArrayList<EquipSlot> getEquipslots() {
        return equipslots;
    }

    public void setEquipslots(ArrayList<EquipSlot> equipslots) {
        this.equipslots = equipslots;
    }

    protected Ship target;

    protected double mass;

    protected double turnrate;

    protected boolean ally;

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean playerFire) {
        this.player = playerFire;
    }

    protected boolean player;

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

        initShipTypeAccel();
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
        g.setColor(Color.yellow);
        if(Driver.DEBUG){
            for (ShipComponent component : components) {
                ((Graphics2D)g).draw(component.getEllipse());
            }
        }
        for(EquipSlot equip: equipslots){
            equip.drawImage(g);
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

    public void addEquipSlot(int x, int y){
        EquipSlot e = new EquipSlot(x, y, ally);
        e.setXY(this.location.x, this.location.y);
        equipslots.add(e);
        compNum ++;
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

    protected void corvette(){
        type = "Corvette";
        mass = 8;
        turnrate = 0.02;
    }

    protected void destroyer(){
        type = "Destroyer";
        mass = 16;
        turnrate = 0.005;
    }

    @Override
    public void drawImage(Graphics g){
        components.forEach(c -> c.draw(g));
        g.setColor(Color.green);
        g.drawLine(-75, -100, (int)(-75 + 150.0 * health / MAXHEALTH ), -100);
    }

    protected ShipComponent makeComponent(BufferedImage image, int c_x, int c_y){
        ShipComponent c = new ShipComponent(image, 0 , 0);
        c.setPivot(c_x, c_y);
        return c;
    }

    private int compNum = -1;
    protected void setEquipment(Equip e){
        equipslots.get(compNum).setEquip(e);
    }

    public void revive(){
        this.health = MAXHEALTH;
        this.alive = true;
        this.target = null;
        for(EquipSlot equipSlot: equipslots){
            equipSlot.getEquip().setTarget(null);
        }
        GameState.getArena().addShip(this);
    }
}
