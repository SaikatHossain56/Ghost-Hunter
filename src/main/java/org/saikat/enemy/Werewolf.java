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

public class Werewolf extends Enemy{
    private Entity wereWolf;

    @Override
    public void shape(double x, double y) {
        AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
                image("wolf.png"), 4, 64, 64 ,
                Duration.seconds(1), 0, 3
        ));
        texture.loop();
        wereWolf =  entityBuilder().at( x,y).view(texture)
                .with(new Enemy())
                .type(EntityType.ENEMY)
                .bbox(new HitBox(BoundingShape.box(46, 50)))
                .collidable()
                .buildAndAttach();
        wereWolf.setProperty("reward", 300);

        hpBar();

    }

    @Override
    void hpBar(){
        Rectangle r1 = new Rectangle(32, 3, Color.WHITE);
        Rectangle r2 = new Rectangle(32, 3, Color.BLUE);
        r1.setStroke(Color.BLACK);
        Group hpBar = new Group(r1, r2);
        Entity hp = entityBuilder().at(wereWolf.getX(), wereWolf.getY() - 10).type(EntityType.HP).with(new Enemy()).view(hpBar).buildAndAttach();

        wereWolf.setProperty("hp", 6000 * 2);
        wereWolf.setProperty("initHp", 6000 * 2);
        wereWolf.setProperty("innerBox",r2);
        wereWolf.setProperty("Bar", hp);
    }
}
