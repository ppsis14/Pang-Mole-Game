package pangMoleGameModel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Background extends Canvas implements Draw2DObject {
    /**
     * @param x is position X of mole on pane.
     * @param y is position Y of mole on pane.
     */
    public Background(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
        setHeight(600);
        setWidth(700);
    }

    @Override
    public void draw() {
        drawSky();
        drawLand();
        drawRocks();
        drawHole();
        drawGrass();
        drawTree();
        drawRope();
    }

    private void drawTree() {

        GraphicsContext treeBody = getGraphicsContext2D();
        Stop[] stopTreeBody = new Stop[] { new Stop(0, Color.rgb(87,61,41)), new Stop(1, Color.rgb(134,110,40))};
        LinearGradient linearGradientTreeBody = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stopTreeBody);

        double[] xPoint = new double[]{650, 665, 590, 610, 665, 660, 700, 700};
        double[] yPoint = new double[]{350, 210, 175, 160, 195, 110, 130, 350};

        treeBody.setFill(linearGradientTreeBody);
        treeBody.fillPolygon(xPoint, yPoint, 8);

        GraphicsContext leaves = getGraphicsContext2D();
        Stop[] stopLeave = new Stop[] {  new Stop(0, Color.rgb(34,168,61,0.9373)), new Stop(1, Color.rgb(41,112,56))};
        LinearGradient linearGradientLeave = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stopLeave);
        leaves.setFill(linearGradientLeave);
        leaves.fillOval(550, 110, 90, 90);
        leaves.fillOval(610, 35, 60, 60);
        leaves.fillOval(600, 80, 100, 100);
        leaves.fillOval(650, 40, 70, 100);
    }

    private void drawRope() {
        GraphicsContext rope = getGraphicsContext2D();
        Stop[] stopRope = new Stop[] { new Stop(0, Color.rgb(193,200,201)), new Stop(1, Color.rgb(135,140,131))};
        LinearGradient linearGradientRope = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stopRope);
        rope.setFill(linearGradientRope);
        for(double i = -4.5; i < 700; i++){
            rope.fillRoundRect(i,140,20,7,10,10);
            rope.strokeRoundRect(i,140,20,7,10,10);
            i += 19.5;
        }
    }

    private void drawRocks() {
        GraphicsContext rock1 = getGraphicsContext2D();
        GraphicsContext rock2 = getGraphicsContext2D();
        Stop[] stopRock1 = new Stop[] { new Stop(0, Color.rgb(193,200,201)), new Stop(1, Color.rgb(135,140,131))};
        LinearGradient linearGradientRock1 = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stopRock1);
        rock1.setFill(linearGradientRock1);
        rock1.fillOval(70, 360, 15, 9);
        rock1.fillOval(250, 430, 25, 15);
        rock1.fillOval(430, 400, 15, 9);
        rock1.fillOval(600, 500, 25, 15);

        Stop[] stopRock2 = new Stop[] { new Stop(0, Color.rgb(135,140,131)), new Stop(1, Color.rgb(193,200,201))};
        LinearGradient linearGradientRock2 = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stopRock2);
        rock2.setFill(linearGradientRock2);
        rock2.fillRoundRect(640, 360, 25, 17, 15, 15);
        rock2.fillRoundRect(385, 540, 15,9, 15,15);
        rock2.fillRoundRect(40, 450, 25, 17, 15,15);
        rock2.fillRoundRect(480, 360, 15, 9, 15,15);
        rock2.fillRoundRect(320, 355, 15, 9, 15,15);
    }

    private void drawGrass() {
        //Draw grass upper of land
        GraphicsContext grassUp = getGraphicsContext2D();
        double[] xPoint = new double[]{0, 10, 20, 30, 40, 50, 60, 70, 90, 80, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190,
                200, 210, 220, 230, 240, 250, 260, 270, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390,
                400, 410, 420, 430, 440, 450, 460, 470, 490, 500, 510, 520, 530, 540, 550, 560, 570, 580, 590,
                600, 610, 620, 630, 640, 650, 660, 670, 690, 695, 700};

        double[] yPoint = new double[]{330, 240, 305, 270, 305, 250, 305, 290, 305, 240, 305, 270, 305, 250, 305, 290, 305, 240, 305,
                270, 305, 250, 305, 290, 305, 240, 305, 270, 305, 250, 305, 290, 305, 240, 305, 270, 305, 250,
                305, 290, 305, 240, 305, 270, 305, 250, 305, 290, 305, 240, 305, 270, 305, 250, 305, 290, 305,
                240, 305, 270, 305, 250, 305, 290, 305, 240, 305, 270, 330};

        Stop[] stopGrass1 = new Stop[] { new Stop(0, Color.rgb(133,216,93,0.9373)), new Stop(1, Color.rgb(43,129,64))};
        LinearGradient lgGrass = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stopGrass1);
        grassUp.setFill(lgGrass);
        grassUp.fillPolygon(xPoint, yPoint, 69);

        //Draw grass bottom of land
        GraphicsContext grassBottom = getGraphicsContext2D();
        double[] xPoint2 = new double[]{0, 10, 20, 30, 40, 50, 60, 70, 90, 80, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190,
                200, 210, 220, 230, 240, 250, 260, 270, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390,
                400, 410, 420, 430, 440, 450, 460, 470, 490, 500, 510, 520, 530, 540, 550, 560, 570, 580, 590,
                600, 610, 620, 630, 640, 650, 660, 670, 690, 695, 700};

        double[] yPoint2 = new double[]{600, 510, 575, 540, 575, 520, 575, 560, 575, 510, 575, 540, 575, 520, 575, 560, 575, 510, 575,
                540, 575, 520, 575, 560, 575, 510, 575, 540, 575, 520, 575, 560, 575, 510, 575, 540, 575, 520,
                575, 560, 575, 510, 575, 540, 575, 520, 575, 560, 575, 510, 575, 540, 575, 520, 575, 560, 575,
                510, 575, 540, 575, 520, 575, 560, 575, 510, 575, 540, 600};

        Stop[] stopGrass2 = new Stop[] { new Stop(0, Color.rgb(34,168,61,0.9373)), new Stop(1, Color.rgb(45,124,60))};
        LinearGradient lgGrass2 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stopGrass2);
        grassBottom.setFill(lgGrass2);
        grassBottom.fillPolygon(xPoint2, yPoint2, 69);
    }

    private void drawSky() {
        GraphicsContext sky = getGraphicsContext2D();
        Stop[] stopSky = new Stop[] { new Stop(0, Color.rgb(82,191,226,0.251)), new Stop(1, Color.rgb(78,179,216))};
        LinearGradient lgSky = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stopSky);
        sky.setFill(lgSky);
        sky.fillRect(0, 0, 700, 330);

        GraphicsContext sun = getGraphicsContext2D();
        Stop[] stopSun = new Stop[] { new Stop(0, Color.rgb(237,233,91,0.7608)), new Stop(1, Color.rgb(237,208,58))};
        LinearGradient lgSun = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stopSun);
        sun.setFill(lgSun);
        sun.fillOval(200, 10, 45, 45);
    }

    private void drawHole() {
        GraphicsContext holeShadow = getGraphicsContext2D();
        Stop[] stopHoleShadow = new Stop[] { new Stop(0, Color.rgb(139,85,35)), new Stop(1, Color.rgb(58,52,16))};
        LinearGradient linearGradientHoleShadow = new LinearGradient(0, 0, 0, 2, true, CycleMethod.NO_CYCLE, stopHoleShadow);
        holeShadow.setFill(linearGradientHoleShadow);
        holeShadow.fillOval(90, 400, 125, 50);
        holeShadow.fillOval(290, 400, 125, 50);
        holeShadow.fillOval(490, 400, 125, 50);
        holeShadow.fillOval(190, 490, 125, 50);
        holeShadow.fillOval(390, 490, 125, 50);

        GraphicsContext holeEdge = getGraphicsContext2D();
        Stop[] stopHoleEdge = new Stop[] { new Stop(0, Color.rgb(139,85,35)), new Stop(1, Color.rgb(155,140,42))};
        LinearGradient linearGradientHoleEdge = new LinearGradient(0, 0, 0, 2, true, CycleMethod.NO_CYCLE, stopHoleEdge);
        holeEdge.setFill(linearGradientHoleEdge);
        holeEdge.fillOval(90, 395, 125, 50);
        holeEdge.fillOval(290, 395, 125, 50);
        holeEdge.fillOval(490, 395, 125, 50);
        holeEdge.fillOval(190, 485, 125, 50);
        holeEdge.fillOval(390, 485, 125, 50);

        GraphicsContext hole = getGraphicsContext2D();
        Stop[] stopHole = new Stop[] { new Stop(0, Color.rgb(105,63,26)), new Stop(1, Color.rgb(134,110,40))};
        LinearGradient linearGradientHole = new LinearGradient(0, 0, 0, 2, true, CycleMethod.NO_CYCLE, stopHole);//Color.rgb(191, 160, 56)
        hole.setFill(linearGradientHole);
        hole.fillOval(100, 400, 105, 40);
        hole.fillOval(300, 400, 105, 40);
        hole.fillOval(500, 400, 105, 40);
        hole.fillOval(200, 490, 105, 40);
        hole.fillOval(400, 490, 105, 40);
    }

    private void drawLand() {
        GraphicsContext land = getGraphicsContext2D();
        Stop[] stopLand = new Stop[] { new Stop(0, Color.rgb(139,85,35)), new Stop(0.5, Color.rgb(165,136,48)), new Stop(1, Color.rgb(191, 160, 56))};
        LinearGradient lgLand = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stopLand);//Color.rgb(191, 160, 56)
        land.setFill(lgLand);
        land.fillRect(0, 330, 700, 280);

    }


}
