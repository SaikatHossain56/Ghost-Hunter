package org.saikat.enemy;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.saikat.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Crow extends Enemy {
    @Override
    public Entity shape(double x, double y) {
        AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
               //1200 * 80
               image("bird.png"), 5, 46, 50 ,
               Duration.seconds(0.8), 0, 4
       ));
       texture.loop();
        Entity crow =  entityBuilder().at( x,y).view(texture)
                .with(new Movable())
                .type(EntityType.ENEMY)
                .bbox(new HitBox(BoundingShape.box(46, 50)))
                .collidable()
                .buildAndAttach();

        Rectangle r1 = new Rectangle(32, 3, Color.WHITE);
        Rectangle r2 = new Rectangle(32, 3, Color.BLUE);
        r1.setStroke(Color.BLACK);
        Group hpBar = new Group(r1, r2);
        Entity hp = entityBuilder().at(crow.getX(), crow.getY() - 10).type(EntityType.HP).with(new Movable()).view(hpBar).buildAndAttach();

        crow.setProperty("hp", 5000);
        crow.setProperty("innerBox",r2);
        crow.setProperty("Bar", hp);


        return crow;
    }
}