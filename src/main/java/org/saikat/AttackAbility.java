package org.saikat;

import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;
import org.saikat.tower.Tower1;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public interface AttackAbility {
    int RANGE = 180;

    //void attack(Entity enemy1);
    void attack(Entity enemy1,Entity enemy2);

    Entity bulletShape();

    default void radar(Entity tower, Tower1 tower1){
        if(tower == null) return;
        long[] lastAttack = {0};

        getGameTimer().runAtInterval( () ->{
            if(!tower.isActive()) return;

            List<Entity> enemies = getGameWorld().getEntitiesByType(EntityType.ENEMY);
            Entity closest = null, closest2 = null;
            double minDist1 = Double.MAX_VALUE;
            double minDist2 = Double.MAX_VALUE;
            for(Entity e : enemies){
                double dx = tower.getX() - e.getX();
                double dy = tower.getY() - e.getY();
                double dist = Math.sqrt(dx * dx + dy * dy);
                if(dist > RANGE) continue;
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
            if(closest != null){
                if(System.currentTimeMillis() - lastAttack[0] > 20) {
                    lastAttack[0] = System.currentTimeMillis();
                    tower1.attack(closest, closest2);
                }
            }
        }, Duration.millis(20));
    }
}
