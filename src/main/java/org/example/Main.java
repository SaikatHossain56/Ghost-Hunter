package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;


import static com.almasb.fxgl.dsl.FXGL.*;

import static com.almasb.fxgl.dsl.FXGL.*;



public class Main extends GameApplication {
    public static double gold = 50.00;
    public static Text scoreText;
    public static int life = 20;
    public static Text lifeText;


    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings.setTitle("1945");
        gameSettings.setWidth(40 * 32);
        gameSettings.setHeight(25 * 32);
        gameSettings.setVersion("0.1");

        //gameSettings.setMainMenuEnabled(true);
        //gameSettings.setGameMenuEnabled(true);

        gameSettings.setManualResizeEnabled(true);
        gameSettings.setFullScreenAllowed(true);
        //gameSettings.setFullScreenFromStart(true);
       // gameSettings.setPreserveResizeRatio(true);
    }
    @Override
    protected void initUI() {

//        HBox vault = new HBox();
//
//        vault.setTranslateX(0);
//        vault.setTranslateY(0);
//        vault.setMinSize(4 * 40, 2 * 40);
//        vault.setStyle("-fx-background-color: darkgray;");
//        vault.setAlignment(Pos.CENTER);
//        vault.setSpacing(10);
//
//        Polygon tower1 = new Polygon(20, 0, 0, 40, 40, 40);
//        Rectangle tower2 = new Rectangle(40, 40, Color.SKYBLUE);
//
//        vault.getChildren().addAll(tower1, tower2);
//        getGameScene().addUINode(vault);
//
//        Field field = new Field();
//        field.moveTower(tower1, tower2);
    }
    @Override
    protected void initGame(){



//        Field field = new Field();
//
//        field.drawGrid();
//        field.shape(14, 0, 5, 2, Color.BROWN);
//        field.shape(0, 17, 17, 1, Color.GREY);
//        field.shape(16, 2, 1, 15, Color.GREY);
//        field.shape(14, 0, 4, 2, Color.GREY);
//
//        field.scoreCard();
//        //getGameWorld().addEntityFactory(new MyFactory());
       setLevelFromMap("wall.tmx");
//
//    //Wave: 1
//        Tower tower = new Tower();



                    for (int i = 1; i <= 30; i++) {

                        getGameTimer().runOnceAfter(() -> {

                            new Enemy(0, 2 * 32 +5);

                        }, Duration.seconds(i));
                    }

//        for (int i = 1; i <= 30; i++) {
//
//            getGameTimer().runOnceAfter(() -> {
//
//                new Enemy(0, 3 * 32 + 5);
//
//            }, Duration.seconds(i));
//        }



    }

    public static void main(String[] args){
        launch(args);
    }
}