package pangMoleGameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

import java.util.Random;

public class Mole extends Animals {
    private boolean hitStatus = false; //to check that mole was hit until can't stand
    private Color moleBodyColor;
    private int resistanceToHit;
    /**
     * @param x is position X of mole on pane.
     * @param y is position Y of mole on pane.
     * @param color input color mole object.
     *
     **/
    public Mole(double x, double y, Color color) {
        setTranslateX(x);
        setTranslateY(y);
        setHeight(170);
        setWidth(100);
        this.moleBodyColor = color;

        //random durability of each mole
        Random randomResistance = new Random();
        int num = randomResistance.nextInt(10);
        resistanceToHit = num != 0? num : 10;
    }

    public int getResistanceToHit() {
        return resistanceToHit;
    }

    public void setResistanceToHit(int resistanceToHit) {
        this.resistanceToHit = resistanceToHit;
    }

    public Color getColorBody() {
        return moleBodyColor;
    }

    public void setHitStatus(boolean hitStatus) { this.hitStatus = hitStatus; }

    @Override
    public void drawBody() {
        GraphicsContext moleBody = getGraphicsContext2D();
        moleBody.setFill(moleBodyColor);
        moleBody.fillRoundRect(5, 5, 60, 75, 60, 60);
        moleBody.strokeRoundRect(5, 5, 60, 75, 60, 60);

        moleBody.setFill(Color.rgb(245, 208, 160, 0.6549));
        moleBody.fillRoundRect(26, 52, 20, 25, 40, 40);

        //hand left
        moleBody.setFill(Color.rgb(215,165,118));
        moleBody.fillOval(10, 50, 13, 6);
        moleBody.fillOval(9, 53, 5, 6);
        moleBody.fillOval(14, 53, 5, 6);
        moleBody.fillOval(19, 53, 5, 6);
        //hand right
        moleBody.fillOval(48, 50, 13, 6);
        moleBody.fillOval(47, 53, 5, 6);
        moleBody.fillOval(52, 53, 5, 6);
        moleBody.fillOval(57, 53, 5, 6);

        //foot left
        moleBody.setFill(Color.rgb(215,165,118));
        moleBody.fillOval(15, 73, 13, 6);
        moleBody.fillOval(14, 76, 5, 6);
        moleBody.fillOval(19, 76, 5, 6);
        moleBody.fillOval(24, 76, 5, 6);
        //foot right
        moleBody.fillOval(43, 73, 13, 6);
        moleBody.fillOval(42, 76, 5, 6);
        moleBody.fillOval(47, 76, 5, 6);
        moleBody.fillOval(52, 76, 5, 6);
    }

    @Override
    public void drawFace() {
        GraphicsContext moleFace = getGraphicsContext2D();

        // nose
        moleFace.setFill(Color.rgb(215,165,118));
        moleFace.fillOval(30, 28, 12, 10);
        moleFace.fillOval(26, 30, 20, 15);
        moleFace.setFill(Color.BLACK);
        moleFace.fillOval(33, 30, 7, 5);
        if (!hitStatus){
            drawNormalEyes();
            //whiskers
            moleFace.strokeLine(18, 32,24, 35);
            moleFace.strokeLine(14, 37,24, 37);
            moleFace.strokeLine(18, 43,24, 39);
            moleFace.strokeLine(48, 35,56, 32);
            moleFace.strokeLine(48, 37,58, 37);
            moleFace.strokeLine(48, 39,56, 43);
        }
        else {
            drawEyesAfterHit();
            drawWound();
            drawStarOnHead();
        }
    }

    private void drawNormalEyes(){
        GraphicsContext moleEye = getGraphicsContext2D();
        //eye left
        moleEye.setFill(Color.BLACK);
        moleEye.fillOval(16, 18, 9, 9);
        moleEye.setFill(Color.WHITE);
        moleEye.fillOval(19, 18, 5, 5);

        // eye right
        moleEye.setFill(Color.BLACK);
        moleEye.fillOval(46, 18, 9, 9);
        moleEye.setFill(Color.WHITE);
        moleEye.fillOval(49, 18, 5, 5);
    }

    private void drawEyesAfterHit(){
        GraphicsContext moleEye = getGraphicsContext2D();
        //eye left
        moleEye.strokeArc(17, 19, 10, 8, 20, -180, ArcType.OPEN);

        // eye right
        moleEye.setFill(Color.BLACK);
        moleEye.fillOval(43, 16.5, 12,12);
        moleEye.setFill(Color.WHITE);
        moleEye.fillOval(46, 19.5, 6, 6);
    }

    public void drawWound(){
        Color woundColor = Color.rgb(134, 82, 95);
        GraphicsContext moleFace = getGraphicsContext2D();
        //wound on head
        moleFace.setFill(woundColor);
        moleFace.fillArc(8, 2, 20, 20,  35, 180, ArcType.OPEN);
        moleFace.fillArc(57, 30, 17, 20, 130, -250, ArcType.OPEN);
        moleFace.strokeArc(8, 2, 20, 20, 35, 180, ArcType.OPEN);
        moleFace.strokeArc(57, 30, 17, 20, 130, -250, ArcType.OPEN);

        Stop[] stopWound = new Stop[] {new Stop(0, Color.rgb(209,98,37)), new Stop(1, Color.rgb(209,186,72,0.5255))};
        LinearGradient linearGradientWound = new LinearGradient(0, 0, 0, 2, true, CycleMethod.NO_CYCLE, stopWound);
        moleFace.setFill(linearGradientWound);
        moleFace.fillArc(11, 4, 15, 12,  35, 180, ArcType.OPEN);
        moleFace.fillArc(60, 32, 13, 14, 130, -250, ArcType.OPEN);

        moleFace.setFill(Color.rgb(113, 80, 48));
        moleFace.fillOval(13, 6, 1,1);
        moleFace.fillOval(15, 8, 1,1);
        moleFace.fillOval(19, 7, 1,1);

        //plaster
        moleFace.setFill(Color.rgb(93, 241, 251));
        moleFace.fillOval(35, 13, 4,10);
        moleFace.fillOval(32, 16, 12,4);

        // blood
        moleFace.setFill(Color.rgb(214, 43, 43));
        moleFace.fillOval(34.5, 34, 4.5, 12);
    }

    public void drawStarOnHead(){
        GraphicsContext star = getGraphicsContext2D();
        double xPoints[] = {40, 47, 50, 52, 60, 54, 58, 50, 42, 46};
        double yPoints[] = {5, 5, 2, 5, 5, 8, 12, 9, 12, 8};
        star.setFill(Color.rgb(250, 208, 52));
        star.fillPolygon(xPoints, yPoints, 10);

        double xPoints2[] = {3, 10, 13, 15, 23, 17, 21, 13, 5, 8};
        double yPoints2[] = {16, 16, 13, 16, 16, 18, 23, 20, 23, 18};
        star.setFill(Color.rgb(231, 191, 50));
        star.fillPolygon(xPoints2, yPoints2, 10);

        double xPoints3[] = {65, 67, 75, 70, 73, 65, 57, 60, 55, 62};
        double yPoints3[] = {18, 21, 21, 24, 28, 26, 28, 24, 21, 21};
        star.setFill(Color.rgb(231, 170, 53));
        star.fillPolygon(xPoints3, yPoints3, 10);
    }

    public boolean isClicked(double mouseX, double mouseY){
        double maxWidth = getTranslateX() + getWidth();
        double maxHeight = getTranslateY() + getHeight();
        if (mouseX < getTranslateX()) return false;
        if (mouseY < getTranslateY()) return false;
        if (mouseX > maxWidth) return false;
        if (mouseY > maxHeight) return false;
        return true;

    }


}
