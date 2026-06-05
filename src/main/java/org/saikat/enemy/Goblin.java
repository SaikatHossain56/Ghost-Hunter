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
    public Entity shape(double x, double y) {
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
        hpBar();

        return goblin;
    }

    @Override
    void hpBar(){
       Rectangle r1 = new Rectangle(32, 3, Color.WHITE);
       Rectangle r2 = new Rectangle(32, 3, Color.BLUE);
       r1.setStroke(Color.BLACK);
       Group hpBar = new Group(r1, r2);
       Entity hp = entityBuilder().at(goblin.getX(), goblin.getY() - 10).type(EntityType.HP).with(new Enemy()).view(hpBar).buildAndAttach();

       goblin.setProperty("hp", 2000);
       goblin.setProperty("innerBox",r2);
       goblin.setProperty("Bar", hp);
   }
   // 19, 0
//   @Override
//    void path1(){
//       if(phase[0] == 0) {
//           if (entity.getY() < 2 * 32) {
//               entity.translateY(2);
//               entity.setRotation(90);
//           }
//           else phase[0] = 1;
//       }
//
//       else if (phase[0] == 1) {
//           if (entity.getX() < 24 * 32) {
//               entity.translateX(2);
//               entity.setRotation(0);
//           } else {
//               phase[0] = 7;
//               return;
//           }
//       }
//   }
}