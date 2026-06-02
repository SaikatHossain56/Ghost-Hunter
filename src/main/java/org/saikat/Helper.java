package org.saikat;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.saikat.tower.Tower;
import org.saikat.tower.Tower1;
import org.saikat.tower.Tower2;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getGameController;

public class Helper {

    public static Entity get(Point2D point, EntityType type) {
        List<Entity> towers = getGameWorld().getEntitiesByType(type);

        for (Entity e : towers) {
            //if(e == null) continue;
            double x = e.getX();
            double y = e.getY();
            double width = e.getWidth();
            double height = e.getHeight();
            boolean check = point.getX() >= x && point.getX() <= x + width
                    && point.getY() >= y && point.getY() <= y + height;
            if (check)
                return e;
        }
        return null;
    }
    public static void addTower(Entity towerSpot){
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

    public static void removeTower(Entity tower){
        getDialogService().showChoiceBox(
                "",
                choice-> {

                    if(choice.equals("Upgrade")) {
                        // later

                    }
                    else if (choice.equals("Remove")) {
                        Entity spot = tower.getObject("getSpot");
                        spot.setProperty("occupied", false);
                        tower. removeFromWorld();

                    }
                    else if(choice.equals("Close"))
                        getGameController().resumeEngine();
                },
                "Upgrade","Remove", "Close"
        );
    }
    public static void gameOver() {

        getDialogService().showChoiceBox(
                "Game Over!!",
                choice-> {

                    if(choice.equals("Play Again")) {
                        getGameController().startNewGame();

                    }
                    else if (choice.equals("Main Menu")) {
                        getGameController().gotoMainMenu();
                    }
                    else if(choice.equals("Exit"))
                        getGameController().exit();
                },
                "Play Again","Main Menu", "Exit"
        );
    }
    public static void levelCompleted(){
        getDialogService().showChoiceBox("Level Completed!!",choice -> {
            if(choice.equals("Play Again"))
                getGameController().startNewGame();
            else if(choice.equals("Main Menu"))
                getGameController().gotoMainMenu();
            else
                getGameController().exit();
        },"Play Again", "Main Menu", "Exit");
    }
}
