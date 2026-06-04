package org.saikat.tower;

import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.saikat.AttackAbility;
import org.saikat.EntityType;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Tower1 extends Tower{

    private int COST = 100;

    public Tower1(){
        range = 200;
        damage = 30;
        texture  = new Texture(FXGLForKtKt.image("tank_bullet.png"));
    }

//    @Override
//    public void getRader(Entity spot) {
//        Circle circle = new Circle(180);
//        circle.setFill(Color.GREEN);
//        circle.setOpacity(0.4);
//
//        entityBuilder().at(spot.getX() + 28, spot.getY() + 68).view(circle).buildAndAttach();
//    }
    public void upgrade(Entity tower){
        range = 300;
        damage = 70;
        COST = 200;

        if(geti("gold") - COST <= 0) {
            getDialogService().showMessageBox("Not Enough Gold");
            return;
        }
        inc("gold", -COST);
        Entity spot = tower.getObject("getSpot");
        tower.removeFromWorld();
        Entity newTower = entityBuilder().at(spot.getX() - 36, spot.getY() - 128)
                .type(EntityType.TOWER_01)
                .viewWithBBox(getAssetLoader().loadTexture("spell2.png", 128, 200))
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

        if(geti("gold") - COST <= 0) {
            getDialogService().showMessageBox("Not Enough Gold");
            return null;
        }

        if(spot.getBoolean("occupied"))
            return null;

        else{
            //getRader(spot);
            spot.setProperty("occupied", true);
            inc("gold", -COST);
            Entity tower = entityBuilder().at(spot.getX() - 36, spot.getY() - 128)
                    .type(EntityType.TOWER_01)
                    .viewWithBBox(getAssetLoader().loadTexture("spell.png", 128, 200))
                    .buildAndAttach();
            tower.setProperty("getSpot", spot);

            return tower;
        }
    }


}
