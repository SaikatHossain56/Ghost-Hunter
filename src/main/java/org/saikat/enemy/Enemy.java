package org.saikat.enemy;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

abstract class Enemy extends Component {

    abstract Entity shape(double x, double y);

//    public static void path(Entity goblin) {
//        int[] phase = {0};
//
//        getGameTimer().runAtInterval(() -> {
//            if (!goblin.isActive())
//                return;
//            if(phase[0] == 0) {
//                if (goblin.getX() < 6 * 32 - 9.5) {
//                    goblin.translateX(1);
//                    goblin.setRotation(0);
//                }
//                else {
//                    phase[0] = 1;
//                }
//            }
//            else if(phase[0] == 1){
//                if(goblin.getY() < 17 * 32 + 7) {
//                    goblin.translateY(1);
//                    goblin.setRotation(90);
//                }
//                else
//                    phase[0] = 2;
//            }
//            else if(phase[0] == 2) {
//                if (goblin.getX() < 11 * 32 - 9.5) {
//                    goblin.translateX(1);
//                    goblin.setRotation(0);
//                }
//                else
//                    phase[0] = 3;
//            }
//            else if(phase[0] == 3) {
//                if(goblin.getY() > 10 * 32 + 5) {
//                    goblin.translateY(-2);
//                    goblin.setRotation(-90);
//                }
//                else
//                    phase[0] = 4;
//            }
//
//            else if(phase[0] == 4){
//                if(goblin.getX() < 15 * 32 + 5) {
//                    goblin.translateX(2);
//                    goblin.setRotation(0);
//                }
//                else
//                    phase[0] = 5;
//            }
//
//            else if(phase[0] == 5) {
//                if(goblin.getY() > 2 * 32 + 5) {
//                    goblin.translateY(-2);
//                    goblin.setRotation(-90);
//                }
//                else
//                    phase[0] = 6;
//            }
//            else if(phase[0] == 6) {
//                if(goblin.getX() < 24 * 32 + 5) {
//                    goblin.translateX(2);
//                    goblin.setRotation(0);
//                }
//                else
//                    phase[0] = 7;
//            }
//            else if(phase[0] == 7) {
//                if(goblin.getY() < 10 * 32 + 5) {
//                    goblin.translateY(2);
//                    goblin.setRotation(90);
//                }
//                else
//                    phase[0] = 8;
//            }
//            else if(phase[0] == 8) {
//                if(goblin.getX() < 31 * 32 + 5) {
//                    goblin.translateX(2);
//                    goblin.setRotation(0);
//                }
//                else
//                    phase[0] = 9;
//            }
//            else if(phase[0] == 9) {
//                if(goblin.getY() > 4 * 32 + 5) {
//                    goblin.translateY(-2);
//                    goblin.setRotation(-90);
//                }
//                else
//                    phase[0] = 10;
//            }
//            else if(phase[0] == 10) {
//                if(goblin.getX() < 38 * 32 + 5) {
//                    goblin.translateX(2);
//                    goblin.setRotation(0);
//                }
//                else
//                    phase[0] = 11;
//            }
//            else if(phase[0] == 11) {
//                if(goblin.getY() < 17 * 32 + 5) {
//                    goblin.translateY(2);
//                    goblin.setRotation(90);
//                }
//                else
//                    phase[0] = 12;
//            }
//            else if(phase[0] == 12) {
//                if(goblin.getX() > 15 * 32 + 5) {
//                    goblin.translateX(-2);
//                    goblin.setRotation(0);
//                    goblin.setScaleX(-1);
//                }
//                else
//                    phase[0] = 13;
//            }
//            else if(phase[0] ==13) {
//                if(goblin.getY() < 23 * 32 + 5) {
//                    goblin.translateY(2);
//                    goblin.setScaleX(1);
//                    goblin.setRotation(90);
//                }
//                else
//                    phase[0] = 14;
//            }
//            else if(phase[0] == 14) {
//                if(goblin.getX() < 45 * 32 + 5) {
//                    goblin.translateX(2);
//                    goblin.setRotation(0);
//                }
//                else
//                    phase[0] = 15;
//            }
//            else {
//
//                goblin.removeFromWorld();
//
//            }
//
//        }, Duration.millis(16));
//
//    }

//    public int[] phase = {0};
//    @Override
//    public void onUpdate(double tpf){
//        if (!entity.isActive())
//            return;
//        path();
//
//    }
//    public void path(){
//        if(phase[0] == 0) {
//            if (entity.getX() < 6 * 32 - 9.5) {
//                entity.translateX(1);
//                entity.setRotation(0);
//            }
//            else {
//                phase[0] = 1;
//            }
//        }
//        else if(phase[0] == 1){
//            if(entity.getY() < 17 * 32 + 7) {
//                entity.translateY(1);
//                entity.setRotation(90);
//            }
//            else
//                phase[0] = 2;
//        }
//        else if(phase[0] == 2) {
//            if (entity.getX() < 11 * 32 - 9.5) {
//                entity.translateX(1);
//                entity.setRotation(0);
//            }
//            else
//                phase[0] = 3;
//        }
//        else if(phase[0] == 3) {
//            if(entity.getY() > 10 * 32 + 5) {
//                entity.translateY(-2);
//                entity.setRotation(-90);
//            }
//            else
//                phase[0] = 4;
//        }
//
//        else if(phase[0] == 4){
//            if(entity.getX() < 15 * 32 + 5) {
//                entity.translateX(2);
//                entity.setRotation(0);
//            }
//            else
//                phase[0] = 5;
//        }
//
//        else if(phase[0] == 5) {
//            if(entity.getY() > 2 * 32 + 5) {
//                entity.translateY(-2);
//                entity.setRotation(-90);
//            }
//            else
//                phase[0] = 6;
//        }
//        else if(phase[0] == 6) {
//            if(entity.getX() < 24 * 32 + 5) {
//                entity.translateX(2);
//                entity.setRotation(0);
//            }
//            else
//                phase[0] = 7;
//        }
//        else if(phase[0] == 7) {
//            if(entity.getY() < 10 * 32 + 5) {
//                entity.translateY(2);
//                entity.setRotation(90);
//            }
//            else
//                phase[0] = 8;
//        }
//        else if(phase[0] == 8) {
//            if(entity.getX() < 31 * 32 + 5) {
//                entity.translateX(2);
//                entity.setRotation(0);
//            }
//            else
//                phase[0] = 9;
//        }
//        else if(phase[0] == 9) {
//            if(entity.getY() > 4 * 32 + 5) {
//                entity.translateY(-2);
//                entity.setRotation(-90);
//            }
//            else
//                phase[0] = 10;
//        }
//        else if(phase[0] == 10) {
//            if(entity.getX() < 38 * 32 + 5) {
//                entity.translateX(2);
//                entity.setRotation(0);
//            }
//            else
//                phase[0] = 11;
//        }
//        else if(phase[0] == 11) {
//            if(entity.getY() < 17 * 32 + 5) {
//                entity.translateY(2);
//                entity.setRotation(90);
//            }
//            else
//                phase[0] = 12;
//        }
//        else if(phase[0] == 12) {
//            if(entity.getX() > 15 * 32 + 5) {
//                entity.translateX(-2);
//                entity.setRotation(0);
//                entity.setScaleX(-1);
//            }
//            else
//                phase[0] = 13;
//        }
//        else if(phase[0] ==13) {
//            if(entity.getY() < 23 * 32 + 5) {
//                entity.translateY(2);
//                entity.setScaleX(1);
//                entity.setRotation(90);
//            }
//            else
//                phase[0] = 14;
//        }
//        else if(phase[0] == 14) {
//            if(entity.getX() < 45 * 32 + 5) {
//                entity.translateX(2);
//                entity.setRotation(0);
//            }
//            else
//                phase[0] = 15;
//        }
//        else {
//            entity.removeFromWorld();
//        }
//    }



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

