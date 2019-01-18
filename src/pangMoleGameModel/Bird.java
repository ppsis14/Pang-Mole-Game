package pangMoleGameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class Bird extends Animals {
    private Color birdBodyColor, birdWingColor;
    /**
     * @param x is position X of bird on pane.
     * @param y is position Y of bird on pane.
     * @param bodyColor input color body of bird object.
     * @param wingColor input color wing of bird object.
     *
     * */
    public Bird(double x, double y, Color bodyColor, Color wingColor){
        setTranslateX(x);
        setTranslateY(y);
        setWidth(100);
        setHeight(100);
        this.birdBodyColor = bodyColor;
        this.birdWingColor = wingColor;
    }

    @Override
    public void draw() {
        super.draw();
        drawWing();
    }

    @Override
    public void drawBody() {
        GraphicsContext bodyBird = getGraphicsContext2D();
        bodyBird.setFill(birdBodyColor);
        bodyBird.fillArc(10, 10, 50, 40, 10, -180, ArcType.CHORD);
        bodyBird.fillOval(10,10, 40, 40);
        //tail
        double[] xPoint = new double[]{60, 63, 66};
        double[] yPoint = new double[]{26, 18, 20};
        double[] xPoint2 = new double[]{60, 70, 75};
        double[] yPoint2 = new double[]{26, 22, 25};
        bodyBird.fillPolygon(xPoint, yPoint, 3);
        bodyBird.strokePolygon(xPoint, yPoint, 3);
        bodyBird.fillPolygon(xPoint2, yPoint2, 3);
        bodyBird.strokePolygon(xPoint2, yPoint2, 3);

    }

    private void drawWing(){
        GraphicsContext birdWing = getGraphicsContext2D();
        //wing
        birdWing.setFill(birdWingColor);
        birdWing.fillOval(40,30, 5, 4);
        birdWing.strokeOval(40,30, 5, 4);
        birdWing.fillOval(40,33, 8, 4);
        birdWing.strokeOval(40,33, 8, 4);
        birdWing.fillOval(41,36, 8, 4);
        birdWing.strokeOval(41,36, 8, 4);
        birdWing.fillArc(35, 31, 10, 10, 90, 250, ArcType.OPEN);
        birdWing.strokeArc(35, 31, 10, 10, 90, 250, ArcType.OPEN);
    }

    @Override
    public void drawFace() {
        GraphicsContext faceBird = getGraphicsContext2D();
        //draw eye
        faceBird.setFill(Color.WHITE);
        faceBird.fillOval(20, 20, 10, 13);
        faceBird.strokeOval(20, 20, 10, 13);
        faceBird.setFill(Color.BLACK);
        faceBird.fillOval(23, 23, 3, 3);
        //draw mouth
        double[] xPoint = new double[]{5, 15, 20};
        double[] yPoint = new double[]{40, 32, 43};

        Stop[] stopMouth = new Stop[] { new Stop(0, Color.rgb(250,244,66)), new Stop(1, Color.rgb(226,204,64))};
        LinearGradient linearGradientMouth = new LinearGradient(0, 0, 2, 2, true, CycleMethod.NO_CYCLE, stopMouth);
        faceBird.setFill(linearGradientMouth);
        faceBird.fillPolygon(xPoint, yPoint, 3);
        faceBird.strokePolygon(xPoint, yPoint, 3);
    }
}
