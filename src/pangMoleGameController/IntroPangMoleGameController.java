package pangMoleGameController;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import pangMoleGameModel.*;
import java.io.IOException;
import java.util.ArrayList;

public class IntroPangMoleGameController {
    @FXML private Pane pane;
    @FXML private Button playGame;
    @FXML private Label nameGame, nameGame2;

    private Background background;
    private Clouds clouds;
    private Group moleGroup;
    private ArrayList<Mole> moles = new ArrayList<>();
    private Animals monkey;
    private BananaItem bananaItem;

    @FXML
    public void handleButtonPlay(javafx.event.ActionEvent event){
        Button b = (Button) event.getSource();

        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pangMoleGameView/howToPlay.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 700, 600));

            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
        moleGroup = new Group();
        background = new Background(0,0);
        clouds = new Clouds(-500, 0);
        moles.add(new Mole(120, 360, Color.rgb(149,110,76)));
        moles.add(new Mole(420, 450, Color.rgb(167, 118, 110)));
        moles.add(new Mole(320, 360, Color.rgb(126, 175, 138)));
        moles.add(new Mole(220, 450, Color.rgb(100, 162, 191)));
        moles.add(new Mole(520, 360, Color.rgb(149, 148, 148)));
        moleGroup.getChildren().addAll(moles);
        monkey = new Monkey(710, 120);
        bananaItem = new BananaItem(-10, 110);

        display();
    }

    private void display(){
        // for clear all canvas that in pane
        pane.getChildren().clear();
        //draw all object
        background.draw();
        clouds.draw();
        monkey.draw();
        bananaItem.draw();

        // for loop to draw all mole in array list
        for (int i = 0; i < moles.size(); i++){
            if (i == 1 || i == 3){
                moles.get(i).setHitStatus(true);
            }
            moles.get(i).draw();
        }

        // to show object on pane
        pane.getChildren().addAll(background, clouds, moleGroup, nameGame, nameGame2, monkey, playGame, bananaItem);
        cloudAnimation();
        monkeyAnimation();

        moleRotate(moles.get(2), 10, 5);
        moleRotate(moles.get(4), -15, -10);
        jumpMole(moles.get(1), 700, 580, 470, 535);
        jumpMole(moles.get(3),0,580, 270, 535);

        TranslateTransition nameMove = new TranslateTransition(Duration.seconds(2), nameGame);
        nameMove.setFromX(nameGame.getTranslateX());
        nameMove.setToX(nameGame.getTranslateX() - 100);
        nameMove.setCycleCount(10);
        nameMove.setAutoReverse(true);
        nameMove.play();

        bananaHitMoleAnimation();

    }

    private void bananaHitMoleAnimation(){
        Timeline animation = new Timeline();
        animation.setCycleCount(1);
        KeyFrame bananaForward = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TranslateTransition bananaMove = new TranslateTransition(Duration.seconds(2), bananaItem);
                bananaMove.setFromX(bananaItem.getTranslateX());
                bananaMove.setToX(bananaItem.getTranslateX() + 110);
                bananaMove.playFromStart();
            }
        });

        KeyFrame bananaDrop = new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TranslateTransition bananaMove = new TranslateTransition(Duration.seconds(2), bananaItem);
                bananaMove.setFromY(bananaItem.getTranslateY());
                bananaMove.setToY(moles.get(0).getTranslateY());
                bananaMove.playFromStart();
            }
        });

        KeyFrame drawMoleHit = new KeyFrame(Duration.seconds(4), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FadeTransition fadeBanana = new FadeTransition(Duration.seconds(0.5), bananaItem);
                fadeBanana.setFromValue(1);
                fadeBanana.setToValue(0);
                fadeBanana.play();

                moles.get(0).drawWound();
                moles.get(0).drawStarOnHead();
            }
        });

        animation.getKeyFrames().addAll(bananaForward, bananaDrop, drawMoleHit);
        animation.play();
    }

    private void cloudAnimation(){
        TranslateTransition cloud = new TranslateTransition(Duration.minutes(1),clouds);
        cloud.setFromX(clouds.getTranslateX());
        cloud.setToX(clouds.getTranslateX() + 1000);
        cloud.setCycleCount(Animation.INDEFINITE);
        cloud.setAutoReverse(true);
        cloud.play();
    }

    private void monkeyAnimation(){
        TranslateTransition monkeyMove = new TranslateTransition(Duration.seconds(10), monkey);
        monkeyMove.setFromX(monkey.getTranslateX());
        monkeyMove.setToX(monkey.getTranslateX() - 800);
        monkeyMove.setCycleCount(Animation.INDEFINITE);
        monkeyMove.setAutoReverse(true);
        monkeyMove.play();
    }

    private void moleRotate(Mole mole, int angle, int distance){

        RotateTransition rotateMole = new RotateTransition(Duration.millis(800), mole);
        rotateMole.setByAngle(angle);
        rotateMole.setCycleCount(Animation.INDEFINITE);
        rotateMole.setAutoReverse(true);

        TranslateTransition moleMove = new TranslateTransition(Duration.seconds(0.8), mole);
        moleMove.setFromX(mole.getTranslateX());
        moleMove.setToX(mole.getTranslateX() + distance);
        moleMove.setCycleCount(Animation.INDEFINITE);
        moleMove.setAutoReverse(true);


        ParallelTransition moleParallel = new ParallelTransition();
        moleParallel.getChildren().addAll(rotateMole, moleMove);
        moleParallel.setCycleCount(Animation.INDEFINITE);
        moleParallel.play();

    }
    private void jumpMole(Mole mole, double mX, double mY, double cuX, double cuY){
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(mX);
        moveTo.setY(mY);
        CubicCurveTo cubicMole = new CubicCurveTo();
        cubicMole.setX(cuX);
        cubicMole.setY(cuY);
        cubicMole.setControlX1(150);
        cubicMole.setControlY1(185);
        cubicMole.setControlX2(180);
        cubicMole.setControlY2(160);
        path.getElements().add(moveTo);
        path.getElements().add(cubicMole);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(2300));
        pt.setPath(path);
        pt.setNode(mole);
        pt.setCycleCount(1);
        pt.setAutoReverse(true);
        pt.play();

    }
}
