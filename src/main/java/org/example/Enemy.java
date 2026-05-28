package org.example;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Enemy {
    double x, y;
    public Enemy(double x, double y){
        this.x = x;
        this.y = y;

       Rectangle rectangle = new Rectangle(30, 30, Color.RED);
      // Texture viking = texture("vikings.png");
       AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
               //1200 * 80
               image("_Run.png"), 10, 120, 80,
               Duration.seconds(0.5), 0, 9
       ));

       texture.loop();

//       viking.setFitWidth(30);
//       viking.setFitHeight(30);
       Entity goblin = entityBuilder().at( x,y - 10).view(texture)
                .type(EntityType.ENEMY)
                .buildAndAttach();
        goblin.setScaleX(1);
        goblin.setScaleY(30.0/80.0);


       EnemyComponent properties = new EnemyComponent();
       goblin.setProperty("properties", properties);
       goblin.getViewComponent().addChild(properties.complexHpBar);
       goblin.setProperty("hp", 5000);
        gameLoop(goblin);
    }

    private void gameLoop(Entity goblin){
        getGameTimer().runAtInterval(()->{
                 if (!goblin.isActive()) return;
            EnemyComponent tmp = goblin.getObject("properties");

                if(goblin.getX() <  16 * 40 + 5) {
                    goblin.setRotation(0);
                    goblin.setX(goblin.getX() + 2);
                }
                else if(goblin.getY() >= 0 ){
                    goblin.setRotation(270);
                    goblin.setY(goblin.getY() - 2);
                }
                else {
                    goblin.removeFromWorld();
                    Main.life --;
                    Main.lifeText.setText("Life: "+ Main.life);
                    if(Main.life <= 0) gameOver();
                }
                // Base damage logic.
        }, Duration.millis(16)); // ~ 60 per sec
    }
    private void gameOver() {
        getGameController().pauseEngine();
        getDialogService().showChoiceBox(
                "Game Over",
                 choice-> {
                    if (choice.equals("Main Menu")) {
                        getGameController().gotoMainMenu();
                    }
                    else if(choice.equals("Restart"))
                        getGameController().startNewGame();


                    Main.life = 20;
                    Main.gold = 50;
                    Main.scoreText.setText("Gold: " + Main.gold);
                    Main.lifeText.setText("Life: " + Main.life);
                    getGameController().resumeEngine();

                },
                "Main Menu", "Restart"
                );
    }
}
