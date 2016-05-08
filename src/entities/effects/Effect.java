package entities.effects;

import entities.GameObject;
import interfaces.Actionable;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public abstract class Effect extends GameObject implements Actionable {

    public void update(){
        this.act();
        this.rangeTick();
    }

    public void rangeTick(){
        health--;
        if(health <= 0){
            this.alive = false;
        }
    }
}
