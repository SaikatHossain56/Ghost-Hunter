package org.saikat;

import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

abstract class Enemy {
    double x, y;

    abstract Entity shape(double x, double y);

//       Rectangle rectangle = new Rectangle(30, 30, Color.RED);
//      // Texture viking = texture("vikings.png");
////       AnimatedTexture texture = new AnimatedTexture( new AnimationChannel(
////               //1200 * 80
////               image("_Run.png"), 10, 120, 80,
////               Duration.seconds(0.5), 0, 9
////       ));
////
////       texture.loop();
//
////       viking.setFitWidth(30);
////       viking.setFitHeight(30);
//       Entity goblin = entityBuilder().at( x,y).view(new Circle(0, 0, 10, Color.RED))
//                .type(EntityType.ENEMY)
//                .buildAndAttach();
////        goblin.setScaleX(1);
////        goblin.setScaleY(30.0/80.0);
//
//
////       EnemyComponent properties = new EnemyComponent();
////       goblin.setProperty("properties", properties);
////       goblin.getViewComponent().addChild(properties.complexHpBar);
////       goblin.setProperty("hp", 5000);
//        path(goblin);
 //   }

//    private void gameLoop(Entity goblin){
//        getGameTimer().runAtInterval(()->{
//                 if (!goblin.isActive()) return;
//            //EnemyComponent tmp = goblin.getObject("properties");
//
//                if(goblin.getX() < 5 * 32 + 5) {
//                   // goblin.setRotation(0);
//                    goblin.setX(goblin.getX() + 1);
//                }
//                else if(goblin.getY() < 17 * 32 + 5 ){
//                    //goblin.setRotation(270);
//                    goblin.setY(goblin.getY() + 1);
//                }
//                else if(goblin.getX() < 18 * 32){
//                    goblin.setX(goblin.getX() + 1);
//
//                }
//                else if(goblin.getY() > 10 * 32 + 5){
//                    goblin.setY(goblin.getY() - 1);
//                }
////                else if(goblin.getX() < 15 * 32 + 5){
////                    goblin.setX(goblin.getX() + 2);
////                }
////                else {
////                    goblin.removeFromWorld();
////                    Main.life --;
////                    Main.lifeText.setText("Life: "+ Main.life);
////                    if(Main.life <= 0) gameOver();
////                }
//                // Base damage logic.
//        }, Duration.millis(16)); // ~ 60 per sec
//    }
protected void path(Entity goblin) {
    int[] phase = {0};

    getGameTimer().runAtInterval(() -> {
        if (!goblin.isActive())
            return;
        if(phase[0] == 0) {
            if (goblin.getX() < 5 * 32 + 5) {
                goblin.translateX(2);
                goblin.setRotation(0);
            }
            else {
                phase[0] = 1;
            }
        }
        else if(phase[0] == 1){
            if(goblin.getY() < 17 * 32 + 5) {
                goblin.translateY(2);
                goblin.setRotation(-90);
            }
            else
                phase[0] = 2;
        }
        else if(phase[0] == 2) {
            if (goblin.getX() < 10 * 32 + 5)
                goblin.translateX(2);
            else
                phase[0] = 3;
        }
        else if(phase[0] == 3) {
            if(goblin.getY() > 10 * 32 + 5) {
                goblin.translateY(-2);
            }
            else
                phase[0] = 4;
        }

        else if(phase[0] == 4){
            if(goblin.getX() < 15 * 32 + 5)
                 goblin.translateX(2);
            else
                phase[0] = 5;
        }

        else if(phase[0] == 5) {
            if(goblin.getY() > 2 * 32 + 5) {
                goblin.translateY(-2);
            }
            else
                phase[0] = 6;
        }
        else if(phase[0] == 6) {
            if(goblin.getX() < 24 * 32 + 5) {
                goblin.translateX(2);
            }
            else
                phase[0] = 7;
        }
        else if(phase[0] == 7) {
            if(goblin.getY() < 10 * 32 + 5) {
                goblin.translateY(2);
            }
            else
                phase[0] = 8;
        }
        else if(phase[0] == 8) {
            if(goblin.getX() < 31 * 32 + 5) {
                goblin.translateX(2);
            }
            else
                phase[0] = 9;
        }
        else if(phase[0] == 9) {
            if(goblin.getY() > 4 * 32 + 5) {
                goblin.translateY(-2);
            }
            else
                phase[0] = 10;
        }
        else if(phase[0] == 10) {
            if(goblin.getX() < 38 * 32 + 5) {
                goblin.translateX(2);
            }
            else
                phase[0] = 11;
        }
        else if(phase[0] == 11) {
            if(goblin.getY() < 17 * 32 + 5) {
                goblin.translateY(2);
            }
            else
                phase[0] = 12;
        }
        else if(phase[0] == 12) {
            if(goblin.getX() > 15 * 32 + 5) {
                goblin.translateX(-2);
            }
            else
                phase[0] = 13;
        }
        else if(phase[0] ==13) {
            if(goblin.getY() < 23 * 32 + 5) {
                goblin.translateY(2);
            }
            else
                phase[0] = 14;
        }
        else if(phase[0] == 14) {
            if(goblin.getX() < 45 * 32 + 5) {
                goblin.translateX(2);
            }
            else
                phase[0] = 15;
        }
        else {

            goblin.removeFromWorld();

//            Main.life--;
//
//            Main.lifeText.setText("Life: " + Main.life);
//
//            if (Main.life <= 0) {
//                gameOver();
//            }
        }

    }, Duration.millis(16));

}
//    private void gameOver() {
//        getGameController().pauseEngine();
//        getDialogService().showChoiceBox(
//                "Game Over",
//                 choice-> {
//                    if (choice.equals("Main Menu")) {
//                        getGameController().gotoMainMenu();
//                    }
//                    else if(choice.equals("Restart"))
//                        getGameController().startNewGame();
//
//
//                    Main.life = 20;
//                    Main.gold = 50;
//                    Main.scoreText.setText("Gold: " + Main.gold);
//                    Main.lifeText.setText("Life: " + Main.life);
//                    getGameController().resumeEngine();
//
//                },
//                "Main Menu", "Restart"
//                );
//    }
}
