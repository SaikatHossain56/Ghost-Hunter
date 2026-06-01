package org.saikat.tower;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import org.saikat.AttackAbility;
import org.saikat.EntityType;
import org.saikat.radarComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class Tower2 extends Tower implements AttackAbility {
    private Entity tower;

    public Entity placeTower(Entity spot){

        if(spot.getBoolean("occupied"))
            return null;

        else{
            spot.setProperty("occupied", true);
            //spawn("tower", spot.getX(), spot.getY());
            tower = entityBuilder().at(spot.getX(), spot.getY())
                    .with(new radarComponent())
                    .type(EntityType.TOWER)
                    .viewWithBBox(getAssetLoader().loadTexture("wildfire.png", 128, 128))
                    .buildAndAttach();
            tower.setProperty("getSpot", spot);

            return tower;
        }
        //newTower.radar(tower, newTower);
    }

    @Override
    public Entity bulletShape() {
        Texture bullet = new Texture(image("tank_bullet.png"));
//        bullet.setFitHeight(10);
//        bullet.setFitWidth(10);
        if(tower == null) return null;
        return entityBuilder().type(EntityType.BULLET)
                .at(tower.getX() - 13, tower.getY() + 25)
                .bbox(new HitBox(BoundingShape.box(10, 10)))
                .view(bullet)
                .collidable()
                .buildAndAttach();
    }

    public void attackActivation(Entity enemy1, Entity enemy2) {
        if(bulletShape() != null)
            AttackAbility.super.attack(bulletShape(), enemy1, enemy2);
    }

    public int damage(Entity arrow) {
        return arrow.getInt("damage");
    }
}
