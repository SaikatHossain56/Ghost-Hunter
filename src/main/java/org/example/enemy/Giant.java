package org.example.enemy;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class Giant extends Enemy {
    @Override
    public Entity shape(double x, double y) {
        return entityBuilder().at( x,y).view(new Rectangle(10, 10, Color.BLUE))
                .type(EntityType.ENEMY)
                .buildAndAttach();
    }
}