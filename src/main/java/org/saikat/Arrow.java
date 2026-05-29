package org.saikat;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;

public class Arrow{

    public Arrow(Entity tower, Entity enemy){
        Polygon shape = new Polygon(0, 0, 10, 0, 5, 10 );
        shape.setFill(Color.RED);
       Circle circle = new Circle(5.0, Color.YELLOW);
        Entity arrow = entityBuilder().at(tower.getCenter())
                .viewWithBBox(shape)
                .buildAndAttach();

        moveArrow(arrow, enemy);
    }

    private void moveArrow(Entity arrow, Entity enemy){

        getGameTimer().runAtInterval( () -> {
            if(! enemy.isActive()){
                arrow.removeFromWorld();
                return;
            }
            arrow.translateTowards(enemy.getCenter(), 5);
            //EnemyComponent hpBar = enemy.getObject("properties");

            if(arrow.distance(enemy) < 5){
                enemy.setProperty(
                        "hp", enemy.getInt("hp") - 1
                );
//                if(enemy.getInt("hp") == 4000) hpBar.complexHpBar.getChildren().remove(hpBar.b5);
//                if(enemy.getInt("hp") == 3000) hpBar.complexHpBar.getChildren().remove(hpBar.b4);
//                if(enemy.getInt("hp") == 2000) hpBar.complexHpBar.getChildren().remove(hpBar.b3);
//                if(enemy.getInt("hp") == 1000) hpBar.complexHpBar.getChildren().remove(hpBar.b2);
//                if(enemy.getInt("hp") <= 0) {
//                    hpBar.complexHpBar.getChildren().remove(hpBar.b1);
//                    enemy.removeFromWorld();
//                    Main.gold += 50;
//                    Main.scoreText.setText("Score: " + Main.gold);
//                }
                if(enemy.getInt("hp") <= 0){
                    enemy.removeFromWorld();
                    arrow.removeFromWorld();
                }

            }

        }, Duration.millis(16));
    }
}
