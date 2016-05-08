package entities.projectiles;

import entities.GameObject;
import interfaces.Actionable;
import interfaces.Team;
import system.MathHelper;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public abstract class Projectile extends GameObject implements Actionable, Team {

    int size;
    double speed;

    boolean ally;

    public int getDamage() {
        return damage;
    }

    int damage;

    public Projectile(){
        health = 100;
    }

    public void update(){
        this.act();
        this.move();
        this.rangeTick();
    }

    protected void init(int x, int y, int damage, double angle, boolean ally) {
        this.setAngle(angle);
        this.initLocation(x, y);
        this.projectileVelocity();
        this.setAlly(ally);
        this.damage = damage;
    }


    /**
     * Sets the bullet velocity, based on it's speed;
     */
    protected void projectileVelocity(){
        this.velocity = MathHelper.getVector(angle, speed);
    }

    public void rangeTick(){
        health--;
        if(health <= 0){
            this.alive = false;
        }
    }



    public boolean isAlly(){
        return ally;
    }

    public void setAlly(boolean ally){
        this.ally = ally;
    }

    public Point.Double getPoint(){
        return location;
    }
}
