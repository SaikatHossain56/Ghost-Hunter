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
    static Tower tower1;
    static Tower tower2;

    public static Entity get(Point2D point, EntityType type1, EntityType type2) {
        List<Entity> spells = getGameWorld().getEntitiesByType(type1);
        List<Entity> wildfires = getGameWorld().getEntitiesByType(type2);

        for (Entity e : spells) {
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

        for (Entity e : wildfires) {
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
    public static Entity get(Point2D point, EntityType type) {
        List<Entity> spots = getGameWorld().getEntitiesByType(type);

        for (Entity e : spots) {
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
                    if(choice.equals("Tower 1")) {
                        tower1 = new Tower1();
                        tower1.radar(tower1.placeTower(towerSpot));
                    }
                    else if (choice.equals("Tower 2")) {
                        tower2 = new Tower2();
                        tower2.radar(tower2.placeTower(towerSpot));
                    }
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

                        if(tower.getType() == EntityType.TOWER_01)
                            tower1.upgrade(tower);

                        else if(tower.getType() == EntityType.TOWER_02)
                            tower2.upgrade(tower);

                    }

                    else if (choice.equals("Remove")) {
                        Entity spot = tower.getObject("getSpot");
                        spot.setProperty("occupied", false);

                        if ((tower.getType() == EntityType.TOWER_01)) {

                            inc("gold", tower1.getCOST() - 30);
                        }
                        else if((tower.getType() == EntityType.TOWER_02)){
                            inc("gold", tower2.getCOST() - 30);
                        }

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
