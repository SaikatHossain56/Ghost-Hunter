package org.saikat;

import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;

public class Attack {
    public void move(Entity arrow, Entity enemy){
        getGameTimer().runAtInterval( () -> {
            if(! enemy.isActive()){
                arrow.removeFromWorld();
                return;
            }
            arrow.translateTowards(enemy.getCenter(), 5);
            System.out.println("HP: " + enemy.getInt("hp"));
            if(arrow.distance(enemy) < 5){
                enemy.setProperty(
                        "hp", enemy.getInt("hp") - damage(arrow)
                );
            }
            if(enemy.getInt("hp") <= 0) {
                arrow.removeFromWorld();
                enemy.removeFromWorld();
            }

        }, Duration.millis(16));
    }
    public int damage(Entity arrow){
        return arrow.getInt("damage");
    }
}
