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
    private Entity crow;

    @Override
    public void shape(double x, double y) {

        AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
               image("bird.png"), 5, 46, 50 ,
               Duration.seconds(0.8), 0, 4
       ));
       texture.loop();

        crow =  entityBuilder().at( x,y).view(texture)
                .with(new Enemy())
                .type(EntityType.ENEMY)
                .bbox(new HitBox(BoundingShape.box(46/2, 50/2)))
                .collidable()
                .buildAndAttach();
        crow.setProperty("reward", 200);

        hpBar();

    }

    @Override
    void hpBar(){
        Rectangle r1 = new Rectangle(32, 3, Color.WHITE);
        Rectangle r2 = new Rectangle(32, 3, Color.BLUE);
        r1.setStroke(Color.BLACK);
        Group hpBar = new Group(r1, r2);
        Entity hp = entityBuilder().at(crow.getX(), crow.getY() - 10).type(EntityType.HP).with(new Enemy()).view(hpBar).buildAndAttach();

        crow.setProperty("hp", 4000 * 2);
        crow.setProperty("initHp", 4000 * 2);
        crow.setProperty("innerBox",r2);
        crow.setProperty("Bar", hp);

    }

}