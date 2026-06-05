package org.saikat;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.saikat.enemy.*;
import org.saikat.tower.SpellTower;

import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class Game extends GameApplication {
    private Entity towerSpot;
    private long cnt;
    private int cntEnemy;


   private boolean wave1;
   private boolean wave2 ;
   private boolean wave3 ;
   private boolean wave4 ;
   private boolean wave5 ;

    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings.setTitle("Ghost Hunter");
        gameSettings.setWidth(40 * 32);
        gameSettings.setHeight(25 * 32);
        gameSettings.setVersion("1.0");

        gameSettings.setMainMenuEnabled(true);
        gameSettings.setGameMenuEnabled(true);

        gameSettings.setManualResizeEnabled(true);
        gameSettings.setFullScreenAllowed(true);

    }
    @Override
    protected void initUI() {

        Entity life = entityBuilder().at(getAppWidth() - 4 * 32, 0)
                .view(getAssetLoader().loadTexture("life.png", 32, 32)).buildAndAttach();
        Entity skull = entityBuilder().at(7 * 32, 0)
                .view(getAssetLoader().loadTexture("skull.png", 150, 200)).buildAndAttach();


        Text uiLife = getUIFactoryService().newText("", Color.RED, 25);
        uiLife.textProperty().bind(getip("life").asString());
        uiLife.setX(37 * 32 + 10);
        uiLife.setY(25);

        Text uiGold = getUIFactoryService().newText("", Color.GOLD, 25);
        uiGold.textProperty().bind(getip("gold").asString());
        uiGold.setX(37 * 32 + 10);
        uiGold.setY(32 + 25);

        addUINode(uiLife);
        addUINode(uiGold);

        Button restart = new Button("Restart");
        restart.setMaxSize(40,20);
        restart.setOnAction(e ->{
            getGameController().startNewGame();
        });
        addUINode(restart, 10, 24 * 32 - 10);
    }

    @Override
    protected void initInput(){
        getInput().addAction(new UserAction("add tower") {

            @Override
            protected void onActionBegin() {
                Point2D point = getInput().getMousePositionWorld();
                towerSpot = Helper.get(point, EntityType.TOWER_SPOT);
                if(towerSpot != null && !towerSpot.getBoolean("occupied"))
                    Helper.addTower(towerSpot);
            }

        }, MouseButton.PRIMARY);

        getInput().addAction(new UserAction("remove tower") {
            @Override
            protected void onActionBegin() {
                Point2D point = getInput().getMousePositionWorld();

                Entity tower = Helper.get(point, EntityType.TOWER_01, EntityType.TOWER_02);
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
            cnt = 0;
            cntEnemy = 0;
            wave1 = true;
            wave2 = false;

        }
    }
    @Override
    protected void onUpdate(double tpf) {

        if(wave1 && (cnt % 60) == 0) {
            Enemy goblin = new Goblin();
            goblin.shape(0, 2 * 32 + 7);

            cntEnemy ++;

            if(cntEnemy == 7) {
                wave1 = false;
                wave2 = true;
                cntEnemy = 0;
            }
        }
        else if(wave2 && (cnt % 60) == 0) {


            Enemy crow = new Crow();
            crow.shape(0, 32 * 2 + 7);

            cntEnemy ++;
            if(cntEnemy == 10) {
                wave2 = false;
                wave3 = true;
                cntEnemy = 0;
            }
        }
        else if(wave3 && (cnt % 60) == 0) {

            Enemy wolf = new Werewolf();
            wolf.shape(0, 2 * 32 + 7);

            cntEnemy ++;
            if(cntEnemy == 5) {
                wave3 = false;
                wave4 = true;
                cntEnemy = 0;
            }
        }
        else if(wave4 && (cnt % 60) == 0) {

            Enemy wolf = new Werewolf();
            wolf.shape(0, 2 * 32 + 7);

            Enemy crow = new Crow();
            crow.shape(0, 32 * 2 + 7);


            cntEnemy ++;
            if(cntEnemy == 10) {
                wave4 = false;
                wave5 = true;
                cntEnemy = 0;
            }
        }
        else if(wave5 && (cnt % 60 == 0)){
            Enemy mother = new Enemy();
            mother.shape(0, 32 * 2 + 7);
            wave5 = false;
        }

        cnt++;

        List<Entity> aliveEnemy = getGameWorld().getEntitiesByType(EntityType.ENEMY);
        boolean checker = !wave1 && !wave2 && !wave3 && !wave4 && !wave5;
        if( checker && aliveEnemy.isEmpty()) {
            Helper.levelCompleted();
        }

        if(geti("life") <= 0) Helper.gameOver();

    }

    @Override
    protected void initPhysics(){
        FXGL.onCollision(EntityType.ENEMY, EntityType.BULLET,  (enemy, bullet) -> {
            if(enemy.isActive() && bullet.isActive()) {

                enemy.setProperty("hp", Math.max((enemy.getInt("hp") - bullet.getInt("damage")), 0));

               Rectangle hpBar = enemy.getObject("innerBox");
               Entity bar = enemy.getObject("Bar");

               double newWidth =  (32.0/ 5000.0) * enemy.getInt("hp");
               hpBar.setWidth(Math.max(newWidth, 0));

               bullet.removeFromWorld();

               if (enemy.getInt("hp") <= 0) {
                   enemy.removeFromWorld();
                   bar.removeFromWorld();
//               }
                   //bullet.removeFromWorld();

                   inc("gold", 100);
               }
            }
        });
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {

        vars.put("gold", 500);
        vars.put("life", 3);
    }


}
