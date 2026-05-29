package org.saikat;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.saikat.enemy.Crow;
import org.saikat.tower.Tower1;


import java.util.List;

import static com.almasb.fxgl.app.GameApplication.launch;
import static com.almasb.fxgl.dsl.FXGL.*;


public class Main extends GameApplication {
    public static double gold = 50.00;
    public static Text scoreText;
    public static int life = 20;
    public static Text lifeText;
    public Entity towerSpot;
    public Tower1 newTower;


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

//            TowerControlBox controlBox = new TowerControlBox();
//            controlBox.setPrefWidth(200);
//
//            // position it on screen — e.g. top right corner
//            addUINode(controlBox, 3 * 32, getAppHeight() - 5 * 32);


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
    protected void initInput(){
        getInput().addAction(new UserAction("add tower") {
            @Override
            protected void onActionBegin() {
                Point2D point = getInput().getMousePositionWorld();

//                getGameWorld().getEntitiesByType(EntityType.TOWER_SPOT)
//                        .stream().filter( e -> {
//                            double x = e.getX();
//                            double y = e.getY();
//                            double width = e.getWidth();
//                            double height = e.getHeight();
//                            return point.getX() >= x && point.getX() <= x + width
//                                    && point.getY() >= y &&  point.getY() <= y + height;
//                                }
//                        ).findFirst().ifPresent(spot -> placeTower(spot));
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
    }
    @Override
    protected void initGame(){

        getGameWorld().addEntityFactory(new Factory());
        setLevelFromMap("wall.tmx");
        //newTower.radar(newTower.tower, newTower);
        for (int i = 1; i <= 100; i++) {

            getGameTimer().runOnceAfter(() -> {

                Crow crow = new Crow();
                crow.path(crow.shape(0, 3 * 32 + 10 ));

            }, Duration.seconds(i));
        }

        }

    public static void main(String[] args){
        launch(args);
    }
}