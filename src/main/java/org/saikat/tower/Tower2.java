package org.saikat.tower;

import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.saikat.AttackAbility;
import org.saikat.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class Tower2 extends Tower {
    private int COST = 150;

    public Tower2(){
        range = 200;
        damage = 50;
        texture  = new Texture(FXGLForKtKt.image("tank_bullet.png"));
    }

    public void upgrade(Entity tower){
        range = 350;
        damage = 120;
        COST = 300;

        if(geti("gold") - COST <= 0) {
            getDialogService().showMessageBox("Not Enough Gold");
            return;
        }
        inc("gold", -COST);
        Entity spot = tower.getObject("getSpot");
        //getRader(spot);
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


//    public void getRader(Entity spot) {
//        Circle circle = new Circle(spot.getX(), spot.getX(), range);
//        circle.setFill(Color.GREEN);
//        circle.setOpacity(0.4);
//        getGameScene().addUINode(circle);
//    }

    @Override
    public Entity placeTower(Entity spot){

        if(geti("gold") <= 0) {
            getDialogService().showMessageBox("Not Enough Gold");
            return null;
        }

        if(spot.getBoolean("occupied"))
            return null;

        else{
            spot.setProperty("occupied", true);
            inc("gold", -COST);
            //getRader(spot);
            Entity tower = entityBuilder().at(spot.getX() - 32, spot.getY() - 44)
                    .type(EntityType.TOWER_02)
                    .viewWithBBox(getAssetLoader().loadTexture("wildfire.png", 128, 128))
                    .buildAndAttach();
            tower.setProperty("getSpot", spot);

            return tower;
        }
    }

}
