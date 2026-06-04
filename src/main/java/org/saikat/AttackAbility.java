package org.saikat;

import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;
import org.saikat.tower.Tower1;
import org.saikat.tower.Tower2;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public interface AttackAbility {

    int RANGE = 180;

    Entity bulletShape(Entity tower);
    void radar(Entity base);

    default Pair detectEnemy(Entity tower, EntityType enemy, int range){

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
    default void attack(Entity bullet, Entity enemy1, Entity enemy2){
        FXGLForKtKt.getGameTimer().runAtInterval( () -> {
            // Arrow 1
            if(! enemy1.isActive()){
                bullet.removeFromWorld();
                return;
            }
            bullet.translateTowards(enemy1.getCenter(), 18);

        }, Duration.millis(100));
    }

    class Pair{
        public Entity first;
        public Entity second;
        public Pair(Entity e1, Entity e2){
            first = e1;
            second = e2;
        }
    }
}
