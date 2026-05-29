package org.saikat;


import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.List;

public class Tower {
    private final double RANGE= 180;

//    public void initInput() {
//        onBtnDown(MouseButton.PRIMARY, () ->{
//            double mouseX = getInput().getMouseXWorld();
//            double mouseY = getInput().getMouseYWorld();
//
//                placeTower(mouseX, mouseY);
//
//        });
//    }
    public void placeTower(double mouseX, double mouseY){
        int cellSize = 40;
        int gridX = (int)(mouseX / cellSize)  * cellSize;
        int gridY = (int)(mouseY / cellSize)  * cellSize;
        Polygon triangle = new Polygon(20, 0, 0, 40, 40, 40);
        triangle.setFill(Color.BLACK);
        Entity tower = entityBuilder().at(gridX, gridY)
                .viewWithBBox(triangle)
                .type(EntityType.TOWER)
                .buildAndAttach();
        radar(tower);
    }
    public void radar(Entity tower){
        //Modifiable inside lambda, 1 sized array.
        //stores last attack time (ms)
        long[] lastAttack = {0};

        getGameTimer().runAtInterval( () ->{
            List<Entity> enemies = getGameWorld().getEntitiesByType(EntityType.ENEMY);
            //List<Entity> hpBars = getGameWorld().getEntitiesByType(EntityType.HPBAR);
              Entity closest = null;
              double minDist = Double.MAX_VALUE;
              for(Entity e : enemies){
                  double dx = tower.getX() - e.getX();
                  double dy = tower.getY() - e.getY();
                  double dist = Math.sqrt(dx * dx + dy * dy);
                  if(dist < RANGE && dist < minDist){
                      closest = e;
                      minDist = dist;
                  }
              }
                 if(closest != null){
                     if(System.currentTimeMillis() - lastAttack[0] > 20) {
                         lastAttack[0] = System.currentTimeMillis();
                          new Arrow(tower, closest);
                     }
                  }
              }, Duration.millis(20));

    }

}
