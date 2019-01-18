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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import pangMoleGameModel.*;
import java.io.IOException;
import java.util.ArrayList;

public class PangMoleGamePlayController {
    @FXML private Pane pane;
    @FXML private Label timeShow, statusPlayer;
    @FXML private Button back;

    private final int START_TIME = 30;
    private int countHitAllMole = 0;
    private Timeline time = new Timeline();
    private boolean statusClick = true;
    private int secondTime = START_TIME;
    private Background background;
    private Clouds clouds;
    private Group moleGroup, birdGroup;
    private ArrayList<Mole> moles = new ArrayList<>();
    private ArrayList<Animals> birds = new ArrayList<>();
    private Monkey monkey;
    private MonkeyKeyAction keyMonkey;
    private BananaItem bananaItem;


    @FXML
    public void initialize(){
        background = new Background(0,0);
        clouds = new Clouds(-500, 0);
        moleGroup = new Group();
        birdGroup = new Group();

        moles.add(new Mole(120, 360, Color.rgb(149,110,76)));
        moles.add(new Mole(320, 360, Color.rgb(167, 99, 116)));
        moles.add(new Mole(520, 360, Color.rgb(84, 117, 149)));
        moles.add(new Mole(220, 450, Color.rgb(101, 173, 123)));
        moles.add(new Mole(420, 450, Color.rgb(214, 115, 12)));

        birds.add(new Bird(710,50, Color.rgb(22, 145, 204), Color.rgb(234,156,184)));
        birds.add(new Bird(800, 85, Color.rgb(204,55,51), Color.rgb(129,188,234)));
        monkey = new Monkey(310, 120);

        moleGroup.getChildren().addAll(moles);
        birdGroup.getChildren().addAll(birds);

        initializeScreen();
        processTime();
        gamePlay();

    }
    @FXML
    public void handleButtonBack(javafx.event.ActionEvent event){
        // clear all objects in group
        moleGroup.getChildren().clear();
        birdGroup.getChildren().clear();

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

    private void gamePlay(){

        keyMonkey = new MonkeyKeyAction(monkey);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                keyMonkey.action();
            }
        };
        timer.start();

        pane.setFocusTraversable(true);
        pane.getParent().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case A:
                        if (monkey.getTranslateX() > 10){
                            keyMonkey.setMoveLeft(true);
                        }
                        break;
                    case D:
                        if (monkey.getTranslateX() + monkey.getWidth() < 670){
                            keyMonkey.setMoveRight(true);
                        }
                        break;
                }
            }
        });

        pane.getParent().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case A:
                    keyMonkey.setMoveLeft(false);
                    break;

                case D:
                    keyMonkey.setMoveRight(false);
                    break;
            }
        });

        pane.getParent().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                if (secondTime == 0 && countHitAllMole != 5 && statusClick){
                    statusPlayer.setText("You  Lost!");
                    pane.getChildren().add(back);
                    statusClick = false;
                }
                else {
                    if (statusClick){
                        if (moles.get(0).isClicked(x,y) && moles.get(0).getResistanceToHit() > -1) clickOnMole(moles.get(0),x,y);
                        else if (moles.get(1).isClicked(x,y) && moles.get(1).getResistanceToHit() > -1) clickOnMole(moles.get(1),x,y);
                        else if (moles.get(2).isClicked(x,y) && moles.get(2).getResistanceToHit() > -1) clickOnMole(moles.get(2),x,y);
                        else if (moles.get(3).isClicked(x,y) && moles.get(3).getResistanceToHit() > -1) clickOnMole(moles.get(3),x,y);
                        else if (moles.get(4).isClicked(x,y) && moles.get(4).getResistanceToHit() > -1) clickOnMole(moles.get(4),x,y);
                        else {
                            bananaItem = new BananaItem(monkey.getTranslateX()+73, 290);
                            bananaItem.draw();
                            pane.getChildren().add(bananaItem);
                            throwBanana(bananaItem,x,y);
                        }
                    }
                }
            }
            private void clickOnMole(Mole mole, double x , double y){
                bananaItem = new BananaItem(monkey.getTranslateX()+73, 290);
                bananaItem.draw();
                pane.getChildren().add(bananaItem);
                throwBanana(bananaItem,x,y);

                //for check that banana item is intersect with mole was selected
                if (bananaItem.intersects(mole.getBoundsInLocal())){
                    moleHitAnimation(mole);
                    if (mole.getResistanceToHit() > 0) {
                        mole.setResistanceToHit(mole.getResistanceToHit()-1);
                    }
                    else {
                        mole.setResistanceToHit(mole.getResistanceToHit()-1);
                        // if resistance of mole was exhausted, will draw wound and star on head
                        drawMoleHit(mole);
                        countHitAllMole++;
                        if (secondTime != 0 && countHitAllMole == 5 && statusClick){
                            statusPlayer.setText("You  Win!");
                            time.stop();
                            pane.getChildren().add(back);
                            statusClick = false;
                        }
                    }
                }
            }
        });
    }
    // draw mole after hit until resistance of mole was exhausted.
    private void drawMoleHit(Mole mole){
        Timeline animation = new Timeline();
        animation.setCycleCount(1);
        KeyFrame fadeOutFadeInMole = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                moleHitAnimation(mole);
            }
        });

        KeyFrame drawNewMoleHit = new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mole.setHitStatus(true);
                mole.draw();
            }
        });
        animation.getKeyFrames().addAll(fadeOutFadeInMole, drawNewMoleHit);
        animation.play();
    }
    // animation of mole when mole was hit but resistance of each mole is enough.
    private void moleHitAnimation(Mole mole){
        Timeline animation = new Timeline();
        animation.setCycleCount(1);
        KeyFrame fadeOutMole = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), mole);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setDelay(Duration.seconds(0.2));
                fadeOut.setCycleCount(1);
                fadeOut.play();
            }
        });

        KeyFrame fadeInMole = new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), mole);
                fadeOut.setFromValue(0);
                fadeOut.setToValue(1);
                fadeOut.setDelay(Duration.seconds(0.1));
                fadeOut.setCycleCount(1);
                fadeOut.play();
            }
        });
        animation.getKeyFrames().addAll(fadeOutMole, fadeInMole);
        animation.play();
    }

    private void processTime() {
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) time.stop();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondTime--;
                timeShow.setText("TIME : "+ String.valueOf(secondTime));
                if (secondTime == 0) {
                    time.stop();
                }
            }
        });
        time.getKeyFrames().add(keyFrame);
        time.playFromStart();
    }

    private void initializeScreen(){
        // for clear all canvas that in pane
        pane.getChildren().clear();
        //draw all object
        background.draw();
        clouds.draw();
        monkey.draw();

        for (Animals mole : moles) {
            mole.draw();
        }

        for (Animals bird : birds) {
            bird.draw();
        }

        // to show object on pane
        pane.getChildren().addAll(background, clouds, moleGroup, birdGroup, monkey, timeShow, statusPlayer);

        cloudAnimation();
        birdAnimation();
    }

    private void throwBanana(BananaItem bananaItem, double toX, double toY){
        Path path = new Path();
        path.getElements().add(new MoveTo(bananaItem.getTranslateX(),bananaItem.getTranslateY()));
        path.getElements().add(new LineTo(toX, toY));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(700));
        pathTransition.setPath(path);
        pathTransition.setNode(bananaItem);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setCycleCount(1);
        pathTransition.play();

        FadeTransition fadeBanana = new FadeTransition(Duration.millis(200), bananaItem);
        fadeBanana.setFromValue(1.0);
        fadeBanana.setToValue(0);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(pathTransition, fadeBanana);
        sequentialTransition.setCycleCount(1);
        sequentialTransition.play();
    }

    private void cloudAnimation(){
        TranslateTransition cloud = new TranslateTransition(Duration.minutes(0.75),clouds);
        cloud.setFromX(clouds.getTranslateX());
        cloud.setToX(clouds.getTranslateX() + 1000);
        cloud.setCycleCount(Animation.INDEFINITE);
        cloud.setAutoReverse(true);
        cloud.play();
    }

    private void birdAnimation(){
        //bird 1
        Path path = new Path();
        path.getElements().add(new MoveTo(750,50));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 380, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 50, 300, 250, 200));
        path.getElements().add(new CubicCurveTo(0, 200, 20, 100, -35, 150));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(20));
        pathTransition.setDelay(Duration.seconds(2.0));
        pathTransition.setNode(birds.get(0));
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.play();

        //bird 2
        TranslateTransition birdMoveForward = new TranslateTransition(Duration.minutes(1), birds.get(1));
        birdMoveForward.setFromX(birds.get(1).getTranslateX());
        birdMoveForward.setToX(birds.get(1).getTranslateX() - 1000);
        birdMoveForward.setCycleCount(Animation.INDEFINITE);
        birdMoveForward.playFromStart();
    }

}
