package org.saikat.tower;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.saikat.AttackAbility;
import org.saikat.EntityType;
import org.saikat.radarComponent;

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
                    .with(new radarComponent())
                    .type(EntityType.TOWER)
                    .viewWithBBox(texture("FieldsTileset.png", 64, 64))
                    .buildAndAttach();
            tower.setProperty("getSpot", spot);

        }
        newTower.radar(tower, newTower);

    }
    public void removeTower(Entity spot){
        tower.removeFromWorld();
    }
    @Override
    public Entity bulletShape() {
        return entityBuilder().type(EntityType.BULLET)
                .at(tower.getCenter())
                .bbox(new HitBox(BoundingShape.box(10, 10)))
                .view(new Circle(5.0, Color.YELLOW))
                .collidable()
                .buildAndAttach();
    }
    @Override
    public void attack(Entity enemy1, Entity enemy2) {
                  Entity arrow1 = bulletShape();
                  Entity arrow2 = entityBuilder().at(tower.getCenter())
                          .viewWithBBox(new Circle(5.0, Color.BLUE))
                          .buildAndAttach();
            getGameTimer().runAtInterval( () -> {
                // Arrow 1
                if(! enemy1.isActive()){
                    arrow1.removeFromWorld();
                    return;
                }
                arrow1.translateTowards(enemy1.getCenter(), 5);
//                if(arrow1.distance(enemy1) < 5){
//                    enemy1.setProperty(
//                            "hp", enemy1.getInt("hp") - 2
//                    );
//                    arrow1.removeFromWorld();
//                }
//                if(enemy1.getInt("hp") <= 0) {
//                    arrow1.removeFromWorld();
//                    enemy1.removeFromWorld();
//                }
                //Arrow 2
//                if(enemy2 != null) {
//                    if (!enemy2.isActive()) {
//                        arrow2.removeFromWorld();
//                        return;
//                    }
//                    arrow2.translateTowards(enemy2.getCenter(), 5);
//                    if (arrow2.distance(enemy2) < 5) {
//                        enemy2.setProperty(
//                                "hp", enemy2.getInt("hp") - 2
//                        );
//                        arrow2.removeFromWorld();
//                    }
//                    if (enemy2.getInt("hp") <= 0) {
//                        arrow2.removeFromWorld();
//                        enemy2.removeFromWorld();
//                    }
//                }

            }, Duration.millis(16));
        }
public int damage(Entity arrow) {
    return arrow.getInt("damage");
}

}
