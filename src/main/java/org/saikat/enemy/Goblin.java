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

public class Goblin extends Enemy {
    private Entity goblin;

    @Override
    public void shape(double x, double y) {
        AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
                //1200 * 80
                image("goblin.png"), 4, 122/4, 36 ,
                Duration.seconds(1), 0, 3
        ));
        texture.loop();
        goblin =  entityBuilder().at( x,y).view(texture)
                .with(new Enemy())
                .type(EntityType.ENEMY)
                .bbox(new HitBox(BoundingShape.box( 122.0/4, 36.0/2)))
                .collidable()
                .buildAndAttach();
        goblin.setProperty("reward", 100);
        hpBar();

    }

    @Override
    void hpBar(){
       Rectangle r1 = new Rectangle(32, 3, Color.WHITE);
       Rectangle r2 = new Rectangle(32, 3, Color.BLUE);
       r1.setStroke(Color.BLACK);
       Group hpBar = new Group(r1, r2);
       Entity hp = entityBuilder().at(goblin.getX(), goblin.getY() - 10).type(EntityType.HP).with(new Enemy()).view(hpBar).buildAndAttach();

       goblin.setProperty("hp", 2000 * 2);
       goblin.setProperty("initHp", 2000 * 2);
       goblin.setProperty("innerBox",r2);
       goblin.setProperty("Bar", hp);
   }
}