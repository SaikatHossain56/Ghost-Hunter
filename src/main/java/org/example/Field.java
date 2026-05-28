package org.example;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class Field {
    int cellSize = 10;
    int row = 80;
    int col = 80;
    Polygon ghost1;
    public void drawGrid(){
        for(int y = 0; y < row; y++){
            for(int x = 0; x < col; x++){
                Rectangle cell = new Rectangle(cellSize, cellSize, Color.DARKGREEN);
                cell.setStroke(Color.BLACK);

                Entity field = entityBuilder().at(
                        x * cellSize, y * cellSize
                ).viewWithBBox(cell).buildAndAttach();
            }
        }
    }
    public void shape(double x, double y, double width, double height, Color color){
        Rectangle rectangle = new Rectangle(width * cellSize, height * cellSize, color);
        Entity entity  = entityBuilder().at(x * cellSize, y * cellSize)
                .viewWithBBox(rectangle).buildAndAttach();
    }
    public void scoreCard() {
        Rectangle rectangle = new Rectangle(3 * cellSize, cellSize, Color.BLACK);
        Text text = new Text();
        text.setText("Score: " + Main.gold);
        text.setFont(Font.font(20));
        text.setFill(Color.WHITE);

        text.setX(10);
        text.setY(30);
        Main.scoreText = text;

        Text text2 = new Text("Life: " + Main.life);
        text2.setFont(Font.font(20));
        text2.setFill(Color.WHITE);
        text2.setX(10);
        text2.setY(30);
        Main.lifeText = text2;

        Group node = new Group(rectangle, text);

        entityBuilder()
                .at(7 * cellSize, 0)
                .view(node)
                .buildAndAttach();
        entityBuilder().at(7 * cellSize, cellSize).view(text2).buildAndAttach();
    }
    public void moveTower(Polygon tower1, Rectangle tower2){
        double[] infoX = {0};
        double[] infoY = {0};

        tower1.setOnMousePressed(e -> {
            infoX[0] = e.getX();
            infoY[0] = e.getY();
            ghost1 = new Polygon(20, 0, 0, 40, 40, 40);
            ghost1.setFill(Color.BLACK);
            ghost1.setOpacity(0.5);
            getGameScene().addUINode(ghost1);
        });

        tower1.setOnMouseDragged(e -> {
            ghost1.setTranslateX(e.getSceneX() - infoX[0]);
            ghost1.setTranslateY(e.getSceneY() - infoY[0]);
        });

        tower1.setOnMouseReleased(e -> {
            double mouseX = e.getSceneX();
            double mouseY = e.getSceneY();
            if(Main.gold >= 50 ) {
                Tower tower = new Tower();
                tower.placeTower(mouseX, mouseY);
                Main.gold -= 50;
                Main.scoreText.setText("Gold: " + Main.gold);
            }
            else {
                getGameController().pauseEngine();
                getDialogService().showMessageBox("Not Enough Gold");
                getGameController().resumeEngine();
            }
            getGameScene().removeUINode(ghost1);
        });

    }
}
