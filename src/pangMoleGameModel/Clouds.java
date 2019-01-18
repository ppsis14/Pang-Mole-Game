package pangMoleGameModel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Clouds extends Canvas implements Draw2DObject {
    public Clouds(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
        setWidth(700);
        setHeight(200);
    }

    @Override
    public void draw() {
        GraphicsContext cloud1 = getGraphicsContext2D();
        GraphicsContext cloud2 = getGraphicsContext2D();
        GraphicsContext cloud3 = getGraphicsContext2D();
        GraphicsContext cloud4 = getGraphicsContext2D();
        Stop[] stopCloud1 = new Stop[] { new Stop(0, Color.grayRgb(255,0.7176)), new Stop(1, Color.rgb(209,216,217))};
        LinearGradient linearGradientCloud1 = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stopCloud1);
        cloud1.setFill(linearGradientCloud1);
        cloud1.fillOval(85, 60, 40, 45);
        cloud1.fillOval(100, 65, 60, 30);
        cloud1.fillOval(135, 70, 60, 15);
        cloud1.fillOval(70, 50, 50, 30);
        cloud1.fillOval(45, 65, 50, 30);

        cloud2.setFill(linearGradientCloud1);
        cloud2.fillOval(385, 30, 40, 45);
        cloud2.fillOval(400, 35, 60, 30);
        cloud2.fillOval(435, 40, 60, 15);
        cloud2.fillOval(370, 20, 50, 30);
        cloud2.fillOval(345, 35, 50, 30);

        Stop[] stopCloud2 = new Stop[] { new Stop(0, Color.rgb(219,226,227)), new Stop(1, Color.rgb(237,246,247))};
        LinearGradient linearGradientCloud2 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stopCloud2);
        cloud3.setFill(linearGradientCloud2);
        cloud3.fillOval(560, 100, 25, 25);
        cloud3.fillOval(515, 115, 40, 10);
        cloud3.fillOval(540, 105, 25, 20);
        cloud3.fillOval(575, 105, 30, 10);

        cloud4.setFill(linearGradientCloud2);
        cloud4.fillOval(235, 155, 25, 25);
        cloud4.fillOval(195, 170, 40, 10);
        cloud4.fillOval(220, 160, 25, 20);
        cloud4.fillOval(245, 165, 30, 10);
    }

}
