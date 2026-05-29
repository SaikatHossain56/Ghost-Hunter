package org.saikat.enemy;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.saikat.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class Goblin extends Enemy {
    @Override
    public Entity shape(double x, double y) {
        Entity goblin = entityBuilder().at( x,y).view(new Circle(0, 0, 10, Color.RED))
                .type(EntityType.ENEMY)
                .buildAndAttach();
        goblin.setProperty("hp", 5000);
        return  goblin;
    }
}