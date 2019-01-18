package pangMoleGameModel;

import javafx.scene.canvas.Canvas;

public abstract class Animals extends Canvas implements Draw2DObject {
    @Override
    public void draw() {
        drawBody();
        drawFace();
    }

    public abstract void drawBody();
    public abstract void drawFace();
}
