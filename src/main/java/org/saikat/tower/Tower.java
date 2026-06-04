package org.saikat.tower;

import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;
import org.saikat.AttackAbility;
import org.saikat.EntityType;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public abstract class Tower{

    double range;
    Entity bullet;
    double damage;
    Texture texture;

    class Pair{
        public Entity first;
        public Entity second;
        public Pair(Entity e1, Entity e2){
            first = e1;
            second = e2;
        }
    }

    abstract public Entity placeTower(Entity spot);
    abstract public int getCOST();

    public void upgrade(Entity tower){
        getDialogService().showMessageBox("Not Upgradable");
    }



    public void radar(Entity tower){
        if(tower == null) return;
        long[] lastAttack = {0};

        getGameTimer().runAtInterval( () ->{
            if(!tower.isActive()) return;

            Pair enemies = detectEnemy(tower, EntityType.ENEMY);


            if(enemies.first != null){
                if(System.currentTimeMillis() - lastAttack[0] > 20) {
                    lastAttack[0] = System.currentTimeMillis();
                    bullet = bulletShape(tower);
                    attack(bullet, enemies.first, enemies.second);

                }
            }
        }, Duration.millis(20));
    }

    Pair detectEnemy(Entity tower, EntityType enemy){

        List<Entity> enemies = getGameWorld().getEntitiesByType(enemy);
        Entity closest = null, closest2 = null;
        double minDist1 = Double.MAX_VALUE;
        double minDist2 = Double.MAX_VALUE;
        for(Entity e : enemies){
            double dx = tower.getX() - e.getX();
            double dy = tower.getY() - e.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);
            if(dist >= range) continue;
            if(dist < minDist1){
                closest2 = closest;
                minDist2 = minDist1;
                closest = e;
                minDist1 = dist;
            }
            else if(dist > minDist1 && dist < minDist2 ){
                closest2 = e;
                minDist2 = dist;
            }
        }
        return new Pair(closest, closest2);

    }

    Entity detectEnemyByHP(Entity tower, EntityType enemy){

        List<Entity> enemies = getGameWorld().getEntitiesByType(enemy);
        Entity target = null;
        int mn = Integer.MAX_VALUE;
        for(Entity e : enemies){
            if(e.getInt("hp") < mn) {
                target = e;
                mn = e.getInt("hp");
            }
        }
        return target;

    }

//    public Entity bulletShape(Entity tower) {
//        if(tower == null) return null;
//        return entityBuilder().type(EntityType.BULLET)
//                .at(tower.getX() + 55, tower.getY() + 10)
//                .bbox(new HitBox(BoundingShape.box(10, 10)))
//                .view(getAssetLoader().loadTexture("spell.png", 128, 200))
//                .collidable()
//                .buildAndAttach();
//    }


    public Entity bulletShape(Entity tower) {
        Texture bullet = new Texture(image("tank_bullet.png"));

        if(tower == null) return null;
        return entityBuilder().type(EntityType.BULLET)
                .at(tower.getX() + 55, tower.getY() + 10)
                .bbox(new HitBox(BoundingShape.box(10, 10)))
                .view(bullet)
                .collidable()
                .buildAndAttach();
    }

    void attack(Entity bullet, Entity enemy1, Entity enemy2){
        FXGLForKtKt.getGameTimer().runAtInterval( () -> {
            // Arrow 1
            if(! enemy1.isActive()){
                bullet.removeFromWorld();
                return;
            }
            bullet.translateTowards(enemy1.getCenter(), 18);

        }, Duration.millis(100));
    }


}
