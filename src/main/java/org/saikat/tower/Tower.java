package org.saikat.tower;

import com.almasb.fxgl.entity.Entity;

public class Tower {
    public Tower(){

    }
    public Tower(Entity towerSpot, Tower1 newTower){
        Entity tower = newTower.placeTower(towerSpot);
        newTower.radar(tower, newTower);
    }
    public Tower(Entity towerSpot, Tower2 newTower){
        Entity tower = newTower.placeTower(towerSpot);
        newTower.radar(tower, newTower);
    }

}
