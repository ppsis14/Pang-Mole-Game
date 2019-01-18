package pangMoleGameModel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class BananaItem extends Canvas implements Draw2DObject {
    /**
     * @param x is position X of mole on pane.
     * @param y is position Y of mole on pane.
     */
    public BananaItem(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
        setHeight(80);
        setWidth(100);
    }

    @Override
    public void draw() {
        GraphicsContext banana = getGraphicsContext2D();
        Stop[] stopBanana = new Stop[] { new Stop(0, Color.rgb(226,220,60)), new Stop(1, Color.rgb(226,204,64))};
        LinearGradient linearGradientBanana = new LinearGradient(0, 0, 0, 2, true, CycleMethod.NO_CYCLE, stopBanana);
        //banana 1
        banana.setFill(linearGradientBanana);
        banana.fillRoundRect(54, 15, 10, 5, 10, 10);
        banana.strokeRoundRect(54, 15, 10, 5, 10, 10);

        banana.fillArc(10, 5, 45, 25, 20, -180, ArcType.CHORD);
        banana.strokeArc(10, 5, 45, 25, 20, -180, ArcType.CHORD);
        banana.strokeLine(20,25, 47, 20);

        banana.setFill(Color.BLACK);
        banana.fillOval(10, 20, 5,6);

        //banana 2
        banana.setFill(linearGradientBanana);
        banana.fillRoundRect(59, 10, 7, 25, 10, 10);
        banana.strokeRoundRect(59, 10, 7, 25, 10, 10);

        banana.fillRoundRect(54, 25, 10, 5, 10, 10);
        banana.strokeRoundRect(54, 25, 10, 5, 10, 10);

        banana.fillArc(5, 15, 50, 25, 20, -180, ArcType.CHORD);
        banana.strokeArc(5, 15, 50, 25, 20, -180, ArcType.CHORD);
        banana.strokeLine(15,35, 47, 30);

        banana.setFill(Color.BLACK);
        banana.fillOval(5, 30, 5,6);
    }

}
