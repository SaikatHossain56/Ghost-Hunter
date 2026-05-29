package org.saikat.enemy;

import com.almasb.fxgl.entity.component.Component;

public class MovingComponent extends Component {
    int[] phase = {0};
    @Override
    public void onUpdate(double tpf){
        if (!entity.isActive())
            return;
        if(phase[0] == 0) {
            if (entity.getX() < 6 * 32 - 9.5) {
                entity.translateX(1);
                entity.setRotation(0);
            }
            else {
                phase[0] = 1;
            }
        }
        else if(phase[0] == 1){
            if(entity.getY() < 17 * 32 + 7) {
                entity.translateY(1);
                entity.setRotation(90);
            }
            else
                phase[0] = 2;
        }
        else if(phase[0] == 2) {
            if (entity.getX() < 11 * 32 - 9.5) {
                entity.translateX(1);
                entity.setRotation(0);
            }
            else
                phase[0] = 3;
        }
        else if(phase[0] == 3) {
            if(entity.getY() > 10 * 32 + 5) {
                entity.translateY(-2);
                entity.setRotation(-90);
            }
            else
                phase[0] = 4;
        }

        else if(phase[0] == 4){
            if(entity.getX() < 15 * 32 + 5) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 5;
        }

        else if(phase[0] == 5) {
            if(entity.getY() > 2 * 32 + 5) {
                entity.translateY(-2);
                entity.setRotation(-90);
            }
            else
                phase[0] = 6;
        }
        else if(phase[0] == 6) {
            if(entity.getX() < 24 * 32 + 5) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 7;
        }
        else if(phase[0] == 7) {
            if(entity.getY() < 10 * 32 + 5) {
                entity.translateY(2);
                entity.setRotation(90);
            }
            else
                phase[0] = 8;
        }
        else if(phase[0] == 8) {
            if(entity.getX() < 31 * 32 + 5) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 9;
        }
        else if(phase[0] == 9) {
            if(entity.getY() > 4 * 32 + 5) {
                entity.translateY(-2);
                entity.setRotation(-90);
            }
            else
                phase[0] = 10;
        }
        else if(phase[0] == 10) {
            if(entity.getX() < 38 * 32 + 5) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 11;
        }
        else if(phase[0] == 11) {
            if(entity.getY() < 17 * 32 + 5) {
                entity.translateY(2);
                entity.setRotation(90);
            }
            else
                phase[0] = 12;
        }
        else if(phase[0] == 12) {
            if(entity.getX() > 15 * 32 + 5) {
                entity.translateX(-2);
                entity.setRotation(0);
                entity.setScaleX(-1);
            }
            else
                phase[0] = 13;
        }
        else if(phase[0] ==13) {
            if(entity.getY() < 23 * 32 + 5) {
                entity.translateY(2);
                entity.setScaleX(1);
                entity.setRotation(90);
            }
            else
                phase[0] = 14;
        }
        else if(phase[0] == 14) {
            if(entity.getX() < 45 * 32 + 5) {
                entity.translateX(2);
                entity.setRotation(0);
            }
            else
                phase[0] = 15;
        }
        else {

            entity.removeFromWorld();

//            Main.life--;
//
//            Main.lifeText.setText("Life: " + Main.life);
//
//            if (Main.life <= 0) {
//                gameOver();
//            }
        }

    }
    }

