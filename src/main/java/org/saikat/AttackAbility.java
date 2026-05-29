package org.saikat;

import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;
import org.saikat.tower.Tower1;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public interface AttackAbility {
    int RANGE = 180;

    void attack(Entity enemy);
    Entity bulletShape();

    default void radar(Entity tower, Tower1 tower1){
        if(tower == null) return;
        long[] lastAttack = {0};

        getGameTimer().runAtInterval( () ->{
            List<Entity> enemies = getGameWorld().getEntitiesByType(EntityType.ENEMY);
            Entity closest = null;
            double minDist = Double.MAX_VALUE;
            for(Entity e : enemies){
                double dx = tower.getX() - e.getX();
                double dy = tower.getY() - e.getY();
                double dist = Math.sqrt(dx * dx + dy * dy);
                if(dist < RANGE && dist < minDist){
                    closest = e;
                    minDist = dist;
                }
            }
            if(closest != null){
                if(System.currentTimeMillis() - lastAttack[0] > 20) {
                    lastAttack[0] = System.currentTimeMillis();
                    tower1.attack(closest);
                }
            }
        }, Duration.millis(20));
    }
}
