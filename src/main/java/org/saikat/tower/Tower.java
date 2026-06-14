package org.saikat.tower;

import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;
import org.saikat.EntityType;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public abstract class Tower{

    int range;
    Entity bullet;
    int damage;

    abstract public Entity placeTower(Entity spot);
    abstract public int getCOST();


    public void upgrade(Entity tower){
        getDialogService().showMessageBox("Not Upgradable!");
    }

    public Entity getBullet(Entity tower) {
        if(tower == null) return null;

        Texture image = getAssetLoader().loadTexture("tank_bullet.png", 16, 16);

        Entity  bullet = entityBuilder().type(EntityType.BULLET)
                .at(tower.getX() + 55, tower.getY() + 10)
                .bbox(new HitBox(BoundingShape.box(1, 1)))
                .view(image)
                .collidable()
                .buildAndAttach();
        bullet.setProperty("damage", 20);
        return bullet;
    }

    public void radar(Entity tower){
        if(tower == null) return;
        long[] lastAttack = {0};

        getGameTimer().runAtInterval( () ->{
            if(!tower.isActive()) return;

            Entity enemy  = detectEnemyByHP(tower);

            if(enemy != null){
                if(System.currentTimeMillis() - lastAttack[0] > 20) {
                    lastAttack[0] = System.currentTimeMillis();
                    bullet = getBullet(tower);
                    attack(bullet, enemy);
                }
            }
        }, Duration.millis(20));
    }

    Entity detectEnemyByHP(Entity tower){

        List<Entity> enemies = getGameWorld().getEntitiesByType(EntityType.ENEMY);
        Entity target = null;
        int mn = Integer.MAX_VALUE;
        for(Entity e : enemies){
            double dx = tower.getX() - e.getX();
            double dy = tower.getY() - e.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);
            if(e.getInt("hp") < mn && (dist <= range)) {
                target = e;
                mn = e.getInt("hp");
            }
        }
        return target;

    }

    void attack(Entity bullet, Entity enemy){
        FXGLForKtKt.getGameTimer().runAtInterval( () -> {
            if(! enemy.isActive()){
                bullet.removeFromWorld();
                return;
            }
            bullet.translateTowards(enemy.getCenter(), 18);

        }, Duration.millis(100));
    }

}
