package org.saikat.enemy;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.saikat.EntityType;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Enemy extends Component{
    private Entity enemy;
    int[] phase = {0};


    public void shape(double x, double y) {

        enemy =  entityBuilder().at( x,y).view(getAssetLoader().loadTexture("motherflame.png", 50, 50))
                .with(new Enemy())
                .type(EntityType.ENEMY)
                .bbox(new HitBox(BoundingShape.box(25, 25)))
                .collidable()
                .buildAndAttach();
        enemy.setProperty("reward", 1000);
        hpBar();

    }

     void hpBar(){
        Rectangle r1 = new Rectangle(32, 3, Color.WHITE);
        Rectangle r2 = new Rectangle(32, 3, Color.RED);
        r1.setStroke(Color.BLACK);
        Group hpBar = new Group(r1, r2);
        Entity hp = entityBuilder().at(enemy.getX(), enemy.getY() - 10).type(EntityType.HP).with(new Enemy()).view(hpBar).buildAndAttach();

         enemy.setProperty("hp", 10000 * 2);
         enemy.setProperty("initHp", 10000 * 2);
         enemy.setProperty("innerBox",r2);
         enemy.setProperty("Bar", hp);
    }

    @Override
    public void onUpdate(double tpf){
        if (!entity.isActive())
            return;
        path1();
        path2();
        path3();
        if(phase[0] == 14) pass();

    }
    void path1(){
        if(phase[0] == 0) {
            if (entity.getX() < 6 * 32 - 32) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else {
                phase[0] = 1;
            }
        }
        else if(phase[0] == 1){
            if(entity.getY() < 17 * 32 - 7) {
                entity.translateY(2);
                if(entity.getType() != EntityType.HP)
                    entity.setRotation(90);
            }
            else
                phase[0] = 2;
        }
        else if(phase[0] == 2) {
            if (entity.getX() < 9 * 32) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 3;
        }
        else if(phase[0] == 3) {
            if(entity.getY() > 10 * 32) {
                entity.translateY(-2);
                entity.setRotation(-90);
            }
            else
                phase[0] = 4;
        }

        else if(phase[0] == 4){
            if(entity.getX() < 14 * 32) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 5;
        }

        else if(phase[0] == 5) {
            if(entity.getY() > 2 * 32) {
                entity.translateY(-2);
                entity.setRotation(-90);
            }
            else
                phase[0] = 6;
        }
        else if(phase[0] == 6) {
            if(entity.getX() < 24 * 32) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 7;
        }
    }
    void path2(){
        if(phase[0] == 7) {
            if(entity.getY() < 10 * 32 ) {
                entity.translateY(2);
                if(entity.getType() != EntityType.HP)
                    entity.setRotation(90);
            }
            else
                phase[0] = 8;
        }
        else if(phase[0] == 8) {
            if(entity.getX() < 31 * 32 ) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 9;
        }
        else if(phase[0] == 9) {
            if(entity.getY() > 4 * 32 ) {
                entity.translateY(-2);
                entity.setRotation(-90);
            }
            else
                phase[0] = 10;
        }
        else if(phase[0] == 10) {
            if(entity.getX() < 37 * 32 + 5) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 11;
        }
        else if(phase[0] == 11) {
            if(entity.getY() < 16 * 32) {
                entity.translateY(2);
                if(entity.getType() != EntityType.HP)
                    entity.setRotation(90);

            }
            else
                phase[0] = 12;
        }

    }
    void path3(){
        if(phase[0] == 12) {
            if(entity.getX() > 16 * 32 ) {
                entity.translateX(-2);
                entity.setRotation(0);
                entity.setScaleX(-1);
            }
            else
                phase[0] = 13;
        }
        else if(phase[0] ==13) {
            if(entity.getY() < 22 * 32 ) {
                entity.translateY(2);
                entity.setScaleX(1);
                if(entity.getType() != EntityType.HP)
                    entity.setRotation(90);
            }
            else
                phase[0] = 14;
        }
        }

    void pass(){
            if(entity.getX() < getGameScene().getAppWidth()) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else {
                if(entity.getType() != EntityType.HP && entity.getX() >= getGameScene().getAppWidth()  ) {
                    inc("life", -1);
                    entity.removeFromWorld();
                }
            }
        }
}

