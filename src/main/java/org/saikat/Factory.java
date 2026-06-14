package org.saikat;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

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
}
