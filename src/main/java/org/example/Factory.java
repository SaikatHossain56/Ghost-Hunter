package org.example;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;


public class Factory implements EntityFactory {
    @Spawns("tower_spot")
    public Entity towerSpot(SpawnData data){
        int w = data.<Integer>get("width");
        int h = data.<Integer>get("height");
        return entityBuilder(data).type(EntityType.TOWER_SPOT)
                .bbox(new HitBox(BoundingShape.box(w, h)))
                .collidable()
                .build();
    }
    @Spawns("tower")
    public Entity newTower(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.TOWER)
                .viewWithBBox(texture("FieldsTileset.png", 64, 64))
                .build();
    }
}
