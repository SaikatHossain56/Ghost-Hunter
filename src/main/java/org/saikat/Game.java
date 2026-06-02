package org.saikat;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.sun.javafx.css.StyleCacheEntry;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.saikat.enemy.Crow;
import org.saikat.enemy.Robot;
import org.saikat.tower.Tower;
import org.saikat.tower.Tower1;
import org.saikat.tower.Tower2;

import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;

public class Game extends GameApplication {
    private Entity towerSpot;
    private Tower1 newTower;
    private long cnt;
    private int cntEnemy;
    Entity enemy1, enemy2 ;


   protected boolean wave1 = true;
   protected boolean wave2 = false;
//    protected int cnt2 = 0;
//    protected int cnt3 = 0;



    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings.setTitle("Ghost Hunter");
        gameSettings.setWidth(40 * 32);
        gameSettings.setHeight(25 * 32);
        gameSettings.setVersion("1.0");

        //gameSettings.setMainMenuEnabled(true);
        //gameSettings.setGameMenuEnabled(true);

        gameSettings.setManualResizeEnabled(true);
        gameSettings.setFullScreenAllowed(true);
        // gameSettings.setFullScreenFromStart(true);
        // gameSettings.setPreserveResizeRatio(true);
    }
    @Override
    protected void initUI() {
        Text uiLife = getUIFactoryService().newText("", Color.RED, 25);
        uiLife.textProperty().bind(getip("life").asString());
        uiLife.setX(37 * 32 + 10);
        uiLife.setY(25);

        Text uiGold = getUIFactoryService().newText("", Color.GOLD, 25);
        uiGold.textProperty().bind(getip("gold").asString());
        uiGold.setX(37 * 32 + 10);
        uiGold.setY(32 + 25);

        Text uiWave = getUIFactoryService().newText("", Color.DARKRED, 25);
        uiWave.textProperty().bind(getip("wave").asString());
        uiWave.setX(37 * 32 + 10);
        uiWave.setY(2 * 32 + 25);

        addUINode(uiLife);
        addUINode(uiGold);
        addUINode(uiWave);

        Button pause = new Button("Pause");
        pause.setMinSize(40,20);
        pause.setOnAction(e ->{
            getGameController().pauseEngine();
        });
        addUINode(pause, 10, 24 * 32 - 10);

        Button resume = new Button("Resume");
        resume.setMinSize(30,20);
        resume.setOnAction(e ->{
            getGameController().resumeEngine();
        });
        addUINode(resume, 60, 24 * 32 - 10);

    }

    @Override
    protected void initInput(){
        getInput().addAction(new UserAction("add tower") {

            @Override
            protected void onActionBegin() {
                Point2D point = getInput().getMousePositionWorld();
                towerSpot = Helper.get(point, EntityType.TOWER_SPOT);
                if(towerSpot != null)
                    Helper.addTower(towerSpot);
            }

        }, MouseButton.PRIMARY);

        getInput().addAction(new UserAction("remove tower") {
            @Override
            protected void onActionBegin() {
                Point2D point = getInput().getMousePositionWorld();

                Entity tower = Helper.get(point, EntityType.TOWER);
                if(tower != null)
                    Helper.removeTower(tower);
            }
        }, MouseButton.SECONDARY);
    }
    @Override
    protected void initGame(){


        getGameWorld().addEntityFactory(new Factory());
        setLevelFromMap("wall.tmx");

        {
            newTower = null;
            cnt = 0;
            cntEnemy = 0;

        }
    }
    @Override
    protected void onUpdate(double tpf) {

        if(wave1 && (cnt % 60) == 0) {
            Robot robot = new Robot();
            enemy1 = robot.shape(0, 2 * 32 + 7);
            Crow crow = new Crow();
            enemy2 = crow.shape(0, 2 *32 + 7);

            cntEnemy ++;

            if(cntEnemy == 10) {
                wave1 = false;
                wave2 = true;
                cntEnemy = 0;
            }
        }
        if(wave2 && (cnt % 60) == 0) {
            Crow crow = new Crow();
            enemy2 = crow.shape(0, 2 *32 + 7);

            cntEnemy ++;
            if(cntEnemy == 10) {
                wave2 = false;
            }
        }

        cnt++;

//        if(cntEnemy == 5){
//            //giant

//        }
        if(geti("life") <= 0) Helper.gameOver();

        getGameTimer().runOnceAfter(()->{

        }, Duration.millis(500));

    }
    @Override
    protected void initPhysics(){
        FXGL.onCollision(EntityType.ENEMY, EntityType.BULLET,  (enemy, bullet) -> {
            if(enemy.isActive() && bullet.isActive()) {

               enemy.setProperty("hp", Math.max(enemy.getInt("hp") - 100, 0));

               Rectangle hpBar = enemy.getObject("innerBox");
               Entity bar = enemy.getObject("Bar");
               double newWidth =  (32.0/ 5000.0) * enemy.getInt("hp");
               hpBar.setWidth(Math.max(newWidth, 0));

               bullet.removeFromWorld();
               if (enemy.getInt("hp") <= 0) {
                   enemy.removeFromWorld();
                   bar.removeFromWorld();
//               }
                   bullet.removeFromWorld();
//               var animChannel = new AnimationChannel(image("explosion2.png"), 16, 128, 128, Duration.seconds(2.6), 0, 15);
//               var animTexture = new AnimatedTexture(animChannel);
//
//               animTexture.play();
                   inc("gold", 100);
                   // enemy.removeFromWorld();


               }
            }
        });
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {

        vars.put("gold", 500);
        vars.put("life", 20);

        vars.put("wave", 1);
        // inc("gold", +6);  // increase god by +6
    }


}
