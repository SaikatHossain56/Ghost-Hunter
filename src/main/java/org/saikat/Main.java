package org.saikat;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.logging.Logger;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.saikat.enemy.Crow;
import org.saikat.enemy.Robot;
import org.saikat.tower.Tower1;


import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.app.GameApplication.launch;
import static com.almasb.fxgl.dsl.FXGL.*;


public class Main extends GameApplication {
    public Entity towerSpot;
    public static Tower1 newTower = null;
    protected int cnt = 0;
    protected int cntEnemy = 0;


    protected int waveCnt = 0;
    protected int cnt2 = 0;
    protected int cnt3 = 0;



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
        Text uiLife = getUIFactoryService().newText("", Color.BLACK, 25);
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
    }

    @Override
    protected void initInput(){
        getInput().addAction(new UserAction("add tower") {
            @Override
            protected void onActionBegin() {
                Point2D point = getInput().getMousePositionWorld();

                List<Entity> towers = getGameWorld().getEntitiesByType(EntityType.TOWER_SPOT);
                for(Entity e : towers){
                    //if(e == null) continue;
                    double x = e.getX();
                    double y = e.getY();
                    double width = e.getWidth();
                    double height = e.getHeight();
                    boolean check = point.getX() >= x && point.getX() <= x + width
                            && point.getY() >= y &&  point.getY() <= y + height;
                    if(check)  {
                        towerSpot = e;
                        break;
                    }
                }
                if(towerSpot != null) {
                    newTower = new Tower1();
                    newTower.placeTower(towerSpot, newTower);
                }
               // else return;

            }
        }, MouseButton.PRIMARY);

        getInput().addAction(new UserAction("remove tower") {
            @Override
            protected void onActionBegin() {
                Point2D point = getInput().getMousePositionWorld();

                List<Entity> towers = getGameWorld().getEntitiesByType(EntityType.TOWER);
                for(Entity e : towers){
                    //if(e == null) continue;
                    double x = e.getX();
                    double y = e.getY();
                    double width = e.getWidth();
                    double height = e.getHeight();
                    boolean check = point.getX() >= x && point.getX() <= x + width
                            && point.getY() >= y &&  point.getY() <= y + height;
                    if(check)  {
                        e.removeFromWorld();
                        Entity spot = e.getObject("getSpot");
                        spot.setProperty("occupied", false);
                        break;
                    }
                }
            }
        }, MouseButton.SECONDARY);
    }
    @Override
    protected void initGame(){


        getGameWorld().addEntityFactory(new Factory());
        setLevelFromMap("wall.tmx");
        }
    @Override
    protected void onUpdate(double tpf) {
        if(cnt == 60) {
            Robot robot = new Robot();
            robot.shape(0, 2 * 32 + 7);
            Crow crow = new Crow();
            crow.shape(0, 2 *32 + 7);
            cnt = 0;
            cntEnemy ++;
        }
        else cnt++;
        if(cntEnemy == 5){
            //giant
        }
    }
   @Override
   protected void initPhysics(){
       FXGL.onCollision(EntityType.ENEMY, EntityType.BULLET,  (bullet, enemy) -> {
           if(enemy.isActive() && bullet.isActive()) {

//               enemy.setProperty("hp", Math.max(enemy.getInt("hp") - 10, 0));
//               Rectangle hpBar = enemy.getObject("innerBox");
//               hpBar.setWidth(Math.max((hpBar.getWidth() - 5), 0));
//               bullet.removeFromWorld();
//               if (enemy.getInt("hp") <= 0) {
//                   enemy.removeFromWorld();
//               }
               bullet.removeFromWorld();
               var animChannel = new AnimationChannel(image("explosion2.png"), 16, 128, 128, Duration.seconds(2.6), 0, 15);
               var animTexture = new AnimatedTexture(animChannel);

               animTexture.play();
               enemy.removeFromWorld();


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


    public static void main(String[] args){
        launch(args);
    }
}