package org.example.enemy;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.enemy.Enemy;
import org.example.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class Goblin extends Enemy {
    @Override
    public Entity shape(double x, double y) {
        return entityBuilder().at( x,y).view(new Circle(0, 0, 10, Color.RED))
                .type(EntityType.ENEMY)
                .buildAndAttach();
    }
}