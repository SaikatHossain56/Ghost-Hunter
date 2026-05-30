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

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public class Robot extends Enemy{
    @Override
    public Entity shape(double x, double y) {
//        AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
//                //1200 * 80
//                image("wolf.png"), 4, 64, 64 ,
//                Duration.seconds(1), 0, 3
//        ));
////        texture.setFitWidth(32);
////        texture.setFitHeight(32);
//        texture.loop();
        AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
                //1200 * 80
                image("enemy_plant.png"), 4, 74, 64 ,
                Duration.seconds(1), 0, 3
        ));
//        texture.setFitWidth(32);
//        texture.setFitHeight(32);
        texture.loop();
        Entity robot =  entityBuilder().at( x,y).view(texture)
                .with(new Movable())
                .type(EntityType.ENEMY)
                .bbox(new HitBox(BoundingShape.box(46, 50)))
                .collidable()
                .buildAndAttach();

        robot.setProperty("hp", 5000);

        Rectangle r1 = new Rectangle(32, 3, Color.YELLOW);
        Rectangle r2 = new Rectangle(32, 3, Color.BLUE);
        r1.setStroke(Color.BLACK);
        Group hpBar = new Group(r1, r2);
        Entity hp = entityBuilder().at(robot.getX(), robot.getY() - 10).with(new Movable()).view(hpBar).buildAndAttach();

        robot.setProperty("innerBox",r2);


        return robot;
    }
}
