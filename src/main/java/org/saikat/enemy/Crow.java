package org.saikat.enemy;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;
import org.saikat.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public class Crow extends Enemy {
    @Override
    public Entity shape(double x, double y) {
        AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
               //1200 * 80
               image("bird.png"), 5, 45, 50 ,
               Duration.seconds(0.8), 0, 4
       ));

       texture.loop();
        Entity crow =  entityBuilder().at( x,y).view(texture)
                .type(EntityType.ENEMY)
                .buildAndAttach();
        crow.setProperty("hp", 5000);
        return crow;
    }
}