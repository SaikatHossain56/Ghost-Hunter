package org.saikat;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;


public class Factory implements EntityFactory {
    @Spawns("tower_spot")
    public Entity towerSpot(SpawnData data){
        int w = data.<Integer>get("width");
        int h = data.<Integer>get("height");
        Entity tmp = entityBuilder(data).type(EntityType.TOWER_SPOT)
                .bbox(new HitBox(BoundingShape.box(w, h)))
                .collidable()
                .build();
        tmp.setProperty("occupied", false);
        return tmp;
    }
//    //@Spawns("tower")
//    public Entity newTower(SpawnData data) {
//        return entityBuilder(data)
//                .type(EntityType.TOWER)
//                .viewWithBBox(texture("FieldsTileset.png", 64, 64))
//                .build();
//    }
//    @Spawns("tower_spot")
//    public Entity towerSpot(SpawnData data) {
//        int w = data.<Integer>get("width");
//        int h = data.<Integer>get("height");
//        int spotNumber = data.<Integer>get("spotNumber"); // custom property in Tiled
//
//        // internally mark spots 1,2,3 as default
//        EntityType type = (spotNumber <= 3)
//                ? EntityType.DEFAULT_SPOT
//                : EntityType.TOWER_SPOT;
//
//        return entityBuilder(data)
//                .type(type)
//                .bbox(new HitBox(BoundingShape.box(w, h)))
//                .collidable()
//                .build();
//    }
}
