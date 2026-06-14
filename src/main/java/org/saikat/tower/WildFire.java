package org.saikat.tower;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import org.saikat.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class WildFire extends Tower {

    private int COST = 150;
    public Texture image;
    private boolean isUpgraded;

    public WildFire(){
        range = 200;
        damage = 50;
    }

    public void upgrade(Entity tower){
        if(isUpgraded) {
            getDialogService().showMessageBox("Already Upgraded.");
            return;
        }

        if(geti("gold") - COST < 0) {
            getDialogService().showMessageBox("Not Enough Gold.");
            return;
        }

        range = 350;
        damage = 120;
        COST = 300;
        isUpgraded = true;

        inc("gold", -COST);
        Entity spot = tower.getObject("getSpot");
        tower.removeFromWorld();

        Entity newTower = entityBuilder().at(spot.getX() - 36, spot.getY() - 128)
                .type(EntityType.TOWER_02)
                .viewWithBBox(getAssetLoader().loadTexture("wildfire2.png", 128, 200))
                .buildAndAttach();
         newTower.setProperty("getSpot", spot);
        radar(newTower);

    }

    @Override
    public int getCOST() {
        return COST;
    }

    @Override
    public Entity placeTower(Entity spot){

        if(geti("gold") - COST<= 0) {
            getDialogService().showMessageBox("Not Enough Gold.");
            return null;
        }

        if(spot.getBoolean("occupied"))
            return null;

        else{
            spot.setProperty("occupied", true);
            inc("gold", -COST);
            Entity tower = entityBuilder().at(spot.getX() - 32, spot.getY() - 44)
                    .type(EntityType.TOWER_02)
                    .viewWithBBox(getAssetLoader().loadTexture("wildfire.png", 128, 128))
                    .buildAndAttach();
            tower.setProperty("getSpot", spot);

            return tower;
        }
    }

    @Override
    public Entity getBullet(Entity tower) {
        if(tower == null) return null;
        if(isUpgraded){
            image = getAssetLoader().loadTexture("blue.png", 16, 16);
        }
        else
            image = getAssetLoader().loadTexture("green.png", 16, 16);
        Entity bullet = entityBuilder().type(EntityType.BULLET)
                .at(tower.getX() + 55, tower.getY() + 10)
                .bbox(new HitBox(BoundingShape.box(1, 1)))
                .view(image)
                .collidable()
                .buildAndAttach();
        bullet.setProperty("damage", damage);



        return bullet;
    }
}
