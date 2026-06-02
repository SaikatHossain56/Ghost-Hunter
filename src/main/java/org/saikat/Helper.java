package org.saikat;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getGameController;

public class Helper {
    public static Entity getTowerSpot(Point2D point) {
        List<Entity> towers = getGameWorld().getEntitiesByType(EntityType.TOWER_SPOT);

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
    public static void gameOver() {

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
}
