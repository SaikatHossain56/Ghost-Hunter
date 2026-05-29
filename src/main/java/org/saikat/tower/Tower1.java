package org.saikat.tower;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.saikat.AttackAbility;
import org.saikat.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Tower1 implements AttackAbility {
    public Entity tower;
    public void placeTower(Entity spot, Tower1 newTower){
        if(spot.getBoolean("occupied"))
            return;
        else{
            spot.setProperty("occupied", true);
            //spawn("tower", spot.getX(), spot.getY());
            tower = entityBuilder().at(spot.getX(), spot.getY())
                    .type(EntityType.TOWER)
                    .viewWithBBox(texture("FieldsTileset.png", 64, 64))
                    .buildAndAttach();

        }
        newTower.radar(tower, newTower);

    }
    @Override
    public Entity bulletShape() {
        return entityBuilder().at(tower.getCenter())
                .viewWithBBox(new Circle(5.0, Color.YELLOW))
                .buildAndAttach();
    }
    @Override
    public void attack(Entity enemy) {
                  Entity arrow = bulletShape();
            getGameTimer().runAtInterval( () -> {
                if(! enemy.isActive()){
                    arrow.removeFromWorld();
                    return;
                }
                arrow.translateTowards(enemy.getCenter(), 5);
                if(arrow.distance(enemy) < 5){
                    enemy.setProperty(
                            "hp", enemy.getInt("hp") - 2
                    );
                    arrow.removeFromWorld();
                }
                if(enemy.getInt("hp") <= 0) {
                    arrow.removeFromWorld();
                    enemy.removeFromWorld();
                }

            }, Duration.millis(16));
        }
public int damage(Entity arrow) {
    return arrow.getInt("damage");
}

}
