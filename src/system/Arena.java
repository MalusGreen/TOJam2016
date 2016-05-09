package system;

import entities.GameObject;
import entities.effects.Damage;
import entities.effects.Effect;
import entities.equipment.Equip;
import entities.projectiles.Projectile;
import entities.ships.Ship;
import interfaces.Updatable;
import system.Scipts.ShipAI;
import system.Scipts.Spawner;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class Arena implements Updatable {
    ArrayList<GameObject> allObjects;

    public ArrayList<Projectile> getAllyProj() {
        return allyProj;
    }

    public void setAllyProj(ArrayList<Projectile> allyProj) {
        this.allyProj = allyProj;
    }

    public ArrayList<Ship> getAllies() {
        return allies;
    }

    public void setAllies(ArrayList<Ship> allies) {
        this.allies = allies;
    }

    public ArrayList<Projectile> getEnemyProj() {
        return enemyProj;
    }

    public void setEnemyProj(ArrayList<Projectile> enemyProj) {
        this.enemyProj = enemyProj;
    }

    public ArrayList<Ship> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Ship> enemies) {
        this.enemies = enemies;
    }

    ArrayList<Projectile> allyProj;
    ArrayList<Ship> allies;

    ArrayList<Projectile> enemyProj;
    ArrayList<Ship> enemies;

    ArrayList<Effect> effects;

    ArrayList<Spawner> spawners;

    HashMap<GameObject, ArrayList> removeMap;
    ArrayList<GameObject> removeList;

    public Arena(){
        init();
    }

    private void init(){
        initLists();
        initSpawners();
    }
    private void initLists(){
        allObjects = new ArrayList<>();
        allyProj = new ArrayList<>();
        allies = new ArrayList<>();
        enemyProj = new ArrayList<>();
        enemies = new ArrayList<>();
        effects = new ArrayList<>();
        removeList = new ArrayList<>();
        spawners = new ArrayList<>();

        removeMap = new HashMap<>();
    }

    private void initSpawners(){
        spawners.add(new Spawner(true));
        spawners.add(new Spawner(false));
    }

    @Override
    public void update() {
        spawn();

        ShipAI.shipDecisions();
        updateObjects();
        updateCollision();
        spawnWeapons();
        remove();
        checkRemove();
    }

    private void spawn(){
        spawners.forEach(s -> s.act());
    }


    private void updateObjects(){
        allObjects.forEach(Updatable::update);
    }

    private void updateCollision(){
        checkShips(allies, enemyProj);
        checkShips(enemies, allyProj);
    }

    private void checkShips(ArrayList<Ship> ships, ArrayList<Projectile> projectiles){
        ships.forEach(
                s -> {
                    projectiles.forEach(
                            p -> {
                                    if(s.collision(p.getPoint())){
                                        s.takeDamage(p.getDamage());

                                        GameState.getArena().addEffect(new Damage((int)p.getLocation().x, (int)p.getLocation().y, p.getDamage()));

                                        this.addRemove(p, projectiles);
                                    }
                            }
                    );
                });
    }

    private void addRemove(GameObject o, ArrayList list){
        removeMap.put(o, list);
        removeList.add(o);
    }

    private void remove(){
        removeList.forEach(
                o -> {
                    removeMap.get(o).remove(o);
                    allObjects.remove(o);
                });

        removeList.clear();
        removeMap.clear();
    }

    private void checkRemove(){
        checkRemoveObjects(allyProj);
        checkRemoveObjects(enemyProj);
        checkRemoveObjects(allies);
        checkRemoveObjects(enemies);
        checkRemoveObjects(effects);
    }

    private void spawnWeapons(){
        enemies.forEach( e -> {
            if(!e.isAlive()){
                e.getEquipslots().forEach( slot -> {
                    try {
                        if(true) {

                            Equip weapon = slot.getEquip().getClass().newInstance();
                            weapon.randomizestats(MathHelper.generateLevel(GameState.getPlayer().getLevel()));
                            GameState.getShop().getInventory().addEquip(weapon);
                        }
                    } catch (InstantiationException | IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                });
            }

        });
    }

    private void checkRemoveObjects(ArrayList list){
        list.forEach(obj -> {
            GameObject o = (GameObject)obj;
            if (!o.isAlive()) {
                addRemove(o, list);
            }
        });
    }


    public void addShip(Ship s){
        if(s.isAlly()){
            allies.add(s);
        }
        else{
            enemies.add(s);
        }

        allObjects.add(s);
    }


    public void addProjectile(Projectile p){
        if(p.isAlly()){
            allyProj.add(p);
        }
        else{
            enemyProj.add(p);
        }

        allObjects.add(p);
    }

    public void addEffect(Effect e){
        effects.add(e);
        allObjects.add(e);
    }

    public void draw(Graphics g){
        allObjects.forEach(object -> object.draw(g));
    }

    public ArrayList<Ship> getTargets(boolean ally){
        return ally ? enemies : allies;
    }

    public void reset(){
        init();
        GameState.getPlayer().getShip().revive();
    }
}
