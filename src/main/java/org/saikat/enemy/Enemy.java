package org.saikat.enemy;

import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;
import org.saikat.AttackAbility;
import org.saikat.EntityType;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public abstract class Enemy extends Component{
    private Entity bullet;

    public abstract Entity shape(double x, double y);


}

