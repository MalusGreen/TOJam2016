package entities;

import interfaces.Drawable;
import interfaces.Updatable;
import system.MathHelper;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public abstract class GameObject implements Updatable, Drawable {
    protected Point.Double location;
    protected Point.Double velocity;

    public double getAngle() {
        return angle;
    }

    protected double angle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name;
    protected String description;

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health = health - damage;
    }

    protected int health;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    protected boolean alive;

    public GameObject(){
        this.setAlive(true);

        initInfo("Default Object", 1, "A generic object.");
        initLocation(0, 0);
        initVelocity(0, 0);
    }

    protected int MAXHEALTH;

    protected void initInfo(String name, int health, String description){
        this.name = name;
        this.description = description;
        this.MAXHEALTH = health;
        this.health = health;
    }

    protected void initLocation(double x, double y){
        location = new Point.Double(x, y);
    }

    protected void initVelocity(double x, double y){
        velocity = new Point.Double(x, y);
    }

    public void draw(Graphics g){
        prepDraw(g);
        drawImage(g);
        deprepDraw(g);
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

    private void prepDraw(Graphics g){
        g.translate((int)location.x, (int)location.y);
    }

    private void deprepDraw(Graphics g){
        g.translate((int)-location.x, (int)-location.y);
    }

    protected void move() {
        location.x += velocity.x;
        location.y += velocity.y;

        velocity = MathHelper.multiply(velocity, 0.96);
    }

    public Point.Double getLocation(){
        return location;
    }

    public void setLocation(Point.Double point){
        this.location = point;
    }
}
