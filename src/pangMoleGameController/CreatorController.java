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
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import pangMoleGameModel.*;
import java.io.IOException;
import java.util.ArrayList;

public class CreatorController {
    @FXML private Pane pane;
    @FXML private Button back;
    @FXML private Label name, createBy;

    private Background background;
    private Clouds clouds;
    private Group moleGroup;
    private ArrayList<Mole> moles = new ArrayList<>();
    private Animals monkey;
    private Animals bird;

    @FXML
    public void handleButtonBack(javafx.event.ActionEvent event){
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
        moles.add(new Mole(420, 450, Color.rgb(167, 99, 116)));
        moles.add(new Mole(320, 360, Color.rgb(84, 117, 149)));
        moles.add(new Mole(220, 450, Color.rgb(89, 122, 104)));
        moleGroup.getChildren().addAll(moles);
        bird = new Bird(700,40, Color.rgb(131, 108, 169), Color.rgb(204,194,118));
        monkey = new Monkey(-10, 120);

        initialScreen();
    }

    private void initialScreen(){
        // for clear all canvas that in pane
        pane.getChildren().clear();
        //draw all object
        background.draw();
        clouds.draw();
        monkey.draw();
        bird.draw();

        // for loop to draw all mole in array list
        for (int i = 0; i < moles.size(); i++){
            if (i == 2 || i == 3){
                moles.get(i).setHitStatus(true);
            }
            moles.get(i).draw();
        }


        name.setText("Thikamporn  Simud\n" + "5910401033");
        // to show object on pane
        pane.getChildren().addAll(background, clouds, moleGroup, back, monkey, name, createBy, bird);

        cloudAnimation();
        monkeyAnimation();
        birdAnimation();
        moleAnimation();

        // label name creator transition
        TranslateTransition nameMove = new TranslateTransition(Duration.seconds(5), name);
        nameMove.setFromX(name.getTranslateX());
        nameMove.setToX(name.getTranslateX() - 450);
        nameMove.play();
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
        TranslateTransition monkeyMove = new TranslateTransition(Duration.seconds(5), monkey);
        monkeyMove.setFromX(monkey.getTranslateX());
        monkeyMove.setToX(monkey.getTranslateX() + 100);
        monkeyMove.setCycleCount(Animation.INDEFINITE);
        monkeyMove.setAutoReverse(true);
        monkeyMove.play();
    }

    private void birdAnimation(){
        TranslateTransition birdMoveForward = new TranslateTransition(Duration.minutes(0.5), bird);
        birdMoveForward.setFromX(bird.getTranslateX());
        birdMoveForward.setToX(bird.getTranslateX() - 1000);
        birdMoveForward.setCycleCount(Animation.INDEFINITE);
        birdMoveForward.playFromStart();
    }

    private void moleAnimation(){
        Timeline animation = new Timeline();
        animation.setCycleCount(1);
        KeyFrame riseUp = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TranslateTransition moveLeft = new TranslateTransition(Duration.minutes(0.5), moles.get(3));
                moveLeft.setFromX(moles.get(3).getTranslateX());
                moveLeft.setToX(moles.get(3).getTranslateX() - 80);
                moveLeft.setCycleCount(1);
                moveLeft.playFromStart();

                TranslateTransition moveRight = new TranslateTransition(Duration.minutes(0.5), moles.get(1));
                moveRight.setFromX(moles.get(1).getTranslateX());
                moveRight.setToX(moles.get(1).getTranslateX() + 280);
                moveRight.setCycleCount(1);
                moveRight.playFromStart();
            }
        });

        KeyFrame jumping = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                jumpMole(moles.get(1), 700, 580, 470, 535);
                jumpMole(moles.get(3),0,580, 270, 535);
            }
        });

        KeyFrame rotate = new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                moleRotate(moles.get(0), 10);
                moleRotate(moles.get(2), -12);
                moleRotate(moles.get(1), 10);
                moleRotate(moles.get(3), -8);
            }
        });

        animation.getKeyFrames().addAll(riseUp, jumping, rotate);
        animation.play();
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
        pt.play();

    }

    private void moleRotate(Mole mole, int angle){
        RotateTransition rotateMole = new RotateTransition(Duration.millis(1000), mole);
        rotateMole.setByAngle(angle);
        rotateMole.setCycleCount(Animation.INDEFINITE);
        rotateMole.setAutoReverse(true);
        rotateMole.playFromStart();

    }

}
