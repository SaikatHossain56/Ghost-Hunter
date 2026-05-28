package org.example;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemyComponent {
    Rectangle hpBarShape, b1, b2, b3, b4, b5;
    Group complexHpBar;
    public EnemyComponent() {
        Rectangle hpBarShape = new Rectangle(30, 10, Color.PINK);
        hpBarShape.setStroke(Color.BLACK);

        b1 = new Rectangle(0, 0, 6, 10);
        b2 = new Rectangle(6, 0, 6, 10);
        b3 = new Rectangle(12, 0, 6, 10);
        b4 = new Rectangle(18, 0, 6, 10);
        b5 = new Rectangle(24, 0, 6, 10);

        b1.setFill(Color.MAROON);
        b2.setFill(Color.MAROON);
        b3.setFill(Color.MAROON);
        b4.setFill(Color.MAROON);
        b5.setFill(Color.MAROON);

        b1.setStroke(Color.BLACK);
        b2.setStroke(Color.BLACK);
        b3.setStroke(Color.BLACK);
        b4.setStroke(Color.BLACK);
        b5.setStroke(Color.BLACK);
        complexHpBar = new Group(hpBarShape, b1, b2, b3, b4, b5);
    }
}
