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
import javafx.scene.text.Text;
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
    private int cnt;
    private int cntEnemy;
    Entity enemy1, enemy2 ;


//    protected int waveCnt = 0;
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
                towerSpot = Helper.getTowerSpot(point);
                if(towerSpot != null)
                    getDialogService().showChoiceBox(
                            "Choose Tower",
                            choice-> {
                                getGameController().resumeEngine();
                                if(choice.equals("Tower 1")) {
                                    new Tower(towerSpot, new Tower1());
                                }
                                else if (choice.equals("Tower 2")) {
                                    new Tower(towerSpot, new Tower2());
                                }
                                else if(choice.equals("Tower 3"))
                                    getGameController().exit();
                                else if(choice.equals("Close")){
                                    getGameController().resumeEngine();

                                }
                            },
                            "Tower 1","Tower 2", "Close"
                    );

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

                getDialogService().showChoiceBox(
                        "",
                        choice-> {

                            if(choice.equals("Upgrade")) {
                                getGameController().startNewGame();

                            }
                            else if (choice.equals("Remove")) {
                                getGameController().gotoMainMenu();
                            }
                            else if(choice.equals("Close"))
                                getGameController().exit();
                        },
                        "Upgrade","Remove", "Close"
                );
//                FXGLDialogService n = new FXGLDialogService();
//                n.showMessageBox("hurry");


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

        if(cnt == 60) {
            Robot robot = new Robot();
            enemy1 = robot.shape(0, 2 * 32 + 7);
            Crow crow = new Crow();
            enemy2 = crow.shape(0, 2 *32 + 7);

            cnt = 0;
            cntEnemy ++;
        }
        else cnt++;
        if(cntEnemy == 5){
            //giant

        }
        if(geti("life") <= 0) Helper.gameOver();

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
//               var animChannel = new AnimationChannel(image("explosion2.png"), 16, 128, 128, Duration.seconds(2.6), 0, 15);
//               var animTexture = new AnimatedTexture(animChannel);
//
//               animTexture.play();
                inc("gold", 100);
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
    private void gameOver() {

        getDialogService().showChoiceBox(
                "Game Over!!",
                choice-> {

                    if(choice.equals("Restart")) {
                        getGameController().startNewGame();

                    }
                    else if (choice.equals("Main Menu")) {
                        getGameController().gotoMainMenu();
                    }
                    else if(choice.equals("Exit"))
                        getGameController().exit();
                },
                "Restart","Main Menu", "Exit"
        );
    }
    private Entity getTowerSpot(Point2D point) {
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
                return e;
            }
        }
        return null;
    }

}
