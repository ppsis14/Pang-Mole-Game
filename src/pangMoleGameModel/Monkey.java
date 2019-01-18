package pangMoleGameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class Monkey extends Animals {
    private Color tissueMonkeyColor;
    /**
     * @param x is position X of monkey on pane.
     * @param y is position Y of monkey on pane.
     */
    public Monkey(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
        setHeight(200);
        setWidth(100);
        tissueMonkeyColor = Color.rgb(221, 209, 179);
    }

    @Override
    public void draw() {
        super.draw();
        drawBananaInHand();
    }

    @Override
    public void drawBody() {
        GraphicsContext monkeyBody = getGraphicsContext2D();
        Stop[] stopMonkey = new Stop[] { new Stop(0, Color.rgb(139,85,35)), new Stop(1, Color.rgb(155,140,42))};
        LinearGradient linearGradientMonkey = new LinearGradient(0, 0, 0, 2, true, CycleMethod.NO_CYCLE, stopMonkey);
        monkeyBody.setFill(linearGradientMonkey);

        //tail
        monkeyBody.fillOval(47,15,15,16);
        monkeyBody.strokeOval(47,15,15,16);
        monkeyBody.setFill(Color.rgb(135,140,131));
        monkeyBody.fillOval(47,19,13,7);
        monkeyBody.strokeOval(47,19,13,7);
        monkeyBody.setFill(linearGradientMonkey);
        monkeyBody.fillRoundRect(48, 15, 6, 50, 10, 10);
        monkeyBody.strokeRoundRect(48, 15, 6, 50, 10, 10);

        //legs
        monkeyBody.fillRoundRect(28, 40, 8, 20, 20, 20);
        monkeyBody.strokeRoundRect(28, 40, 8, 20, 20, 20);
        monkeyBody.fillRoundRect(65, 40, 8, 20, 20, 20);
        monkeyBody.strokeRoundRect(65, 40, 8, 20, 20, 20);
        //feet
        monkeyBody.setFill(tissueMonkeyColor);
        monkeyBody.fillArc(17, 32, 20, 12, 0, -180, ArcType.ROUND);
        monkeyBody.fillArc(63, 32, 20, 12, 0, -180, ArcType.ROUND);
        monkeyBody.strokeArc(17, 32, 20, 12, 0, -180, ArcType.ROUND);
        monkeyBody.strokeArc(63, 32, 20, 12, 0, -180, ArcType.ROUND);

        //arms
        monkeyBody.setFill(linearGradientMonkey);
        monkeyBody.fillRoundRect(30, 130, 10, 30, 20, 20);
        monkeyBody.strokeRoundRect(30, 130, 10, 30, 20, 20);
        monkeyBody.fillRoundRect(63, 130, 10, 30, 20, 20);
        monkeyBody.strokeRoundRect(63, 130, 10, 30, 20, 20);
        //hands
        monkeyBody.setFill(tissueMonkeyColor);
        monkeyBody.fillOval(30, 154, 15, 12);
        monkeyBody.strokeOval(30, 154, 15, 12);
        monkeyBody.fillOval(58, 154, 15, 12);
        monkeyBody.strokeOval(58, 154, 15, 12);

        //body
        monkeyBody.setFill(linearGradientMonkey);
        monkeyBody.fillRoundRect(30, 50, 40, 50, 40, 40);
        monkeyBody.strokeRoundRect(30, 50, 40, 50, 40, 40);
        //left outer ears
        monkeyBody.fillOval(5,95,20,20);
        monkeyBody.strokeOval(5,95,20,20);
        //right outer ears
        monkeyBody.fillOval(75,95,20,20);
        monkeyBody.strokeOval(75,95,20,20);

        //inner ears
        monkeyBody.setFill(tissueMonkeyColor);
        //left
        monkeyBody.fillOval(10,98,15,13);
        monkeyBody.strokeOval(10,98,15,13);
        //right
        monkeyBody.fillOval(75,98,15,13);
        monkeyBody.strokeOval(75,98,15,13);

        //head
        monkeyBody.setFill(linearGradientMonkey);
        monkeyBody.fillOval(20, 85, 60,55);
        monkeyBody.strokeOval(20, 85, 60,55);
    }

    @Override
    public void drawFace() {
        GraphicsContext monkeyFace = getGraphicsContext2D();
        //face frame
        monkeyFace.setFill(Color.rgb(221, 209, 179));
        monkeyFace.fillOval(30, 93, 25,35);
        monkeyFace.fillOval(45, 93, 25,35);
        monkeyFace.fillOval(27, 105, 47,30);

        //eye left
        monkeyFace.setFill(Color.BLACK);
        monkeyFace.fillOval(30, 100, 15, 15);

        monkeyFace.setFill(Color.WHITE);
        monkeyFace.fillOval(33, 103, 5, 5);
        monkeyFace.fillOval(37, 110, 2, 2);

        // eye right
        monkeyFace.setFill(Color.BLACK);
        monkeyFace.fillOval(55, 100, 15, 15);

        monkeyFace.setFill(Color.WHITE);
        monkeyFace.fillOval(61, 103, 5, 5);
        monkeyFace.fillOval(58, 110, 2, 2);

        // nose
        monkeyFace.setFill(Color.rgb(202, 153, 114));
        monkeyFace.fillOval(47, 115, 7, 4);
        monkeyFace.strokeOval(47, 115, 7, 4);

        //cheek
        monkeyFace.setFill(Color.rgb(214, 130, 173, 0.302));
        monkeyFace.fillOval(32, 119, 10, 7);
        monkeyFace.fillOval(60, 119, 10, 7);

        //mouth
        monkeyFace.setFill(Color.rgb(202, 153, 114));
        monkeyFace.fillOval(46, 123, 10, 13);
        monkeyFace.strokeOval(46, 123, 10, 13);
        monkeyFace.setFill(Color.rgb(214, 89, 88));
        monkeyFace.fillOval(48.5, 126, 5, 7);
    }


    private void drawBananaInHand(){
        GraphicsContext banana = getGraphicsContext2D();
        Stop[] stopBanana = new Stop[] { new Stop(0, Color.rgb(250,244,66)), new Stop(1, Color.rgb(226,204,64))};
        LinearGradient linearGradientBanana = new LinearGradient(0, 0, 2, 2, true, CycleMethod.NO_CYCLE, stopBanana);
        //banana 1
        banana.setFill(linearGradientBanana);
        banana.fillRoundRect(77, 145, 10, 5, 10, 10);
        banana.strokeRoundRect(77, 145, 10, 5, 10, 10);
        banana.fillArc(33, 135, 45, 25, 20, -180, ArcType.CHORD);
        banana.strokeArc(33, 135, 45, 25, 20, -180, ArcType.CHORD);
        banana.strokeLine(43,155, 70, 150);
        banana.setFill(Color.BLACK);
        banana.fillOval(33, 150, 5,6);
        //banana 2
        banana.setFill(linearGradientBanana);
        banana.fillRoundRect(82, 140, 7, 25, 10, 10);
        banana.strokeRoundRect(82, 140, 7, 25, 10, 10);
        banana.fillRoundRect(77, 155, 10, 5, 10, 10);
        banana.strokeRoundRect(77, 155, 10, 5, 10, 10);

        banana.fillArc(28, 145, 50, 25, 20, -180, ArcType.CHORD);
        banana.strokeArc(28, 145, 50, 25, 20, -180, ArcType.CHORD);
        banana.strokeLine(38,165, 70, 160);

        banana.setFill(Color.BLACK);
        banana.fillOval(28, 160, 5,6);

    }

    public void goRight(){ setTranslateX(getTranslateX() + 1); }

    public void goLeft(){ setTranslateX(getTranslateX() - 1); }
}
