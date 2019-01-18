package pangMoleGameController;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import pangMoleGameModel.Background;
import pangMoleGameModel.Bird;
import pangMoleGameModel.Clouds;
import pangMoleGameModel.Monkey;
import java.io.IOException;

public class HowToPlayController {
    @FXML
    private Pane pane;
    @FXML private Button startGame, creator;
    @FXML private Label describeGame, describeBox, howToPlay;
    private Bird bird;
    private Background background;
    private Clouds clouds;
    private Monkey monkey;

    @FXML
    public void handleButtonStartGame(javafx.event.ActionEvent event){
        Button b = (Button) event.getSource();

        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pangMoleGameView/pangMoleGamePlay.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 700, 600));

            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void handleButtonCreator(javafx.event.ActionEvent event){
        Button b = (Button) event.getSource();

        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pangMoleGameView/creator.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 700, 600));

            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
        background = new Background(0,0);
        clouds = new Clouds(-500, 0);
        monkey = new Monkey(710, 120);
        bird = new Bird(710,50, Color.rgb(172, 176, 174), Color.rgb(53,169,76));

        initialScreen();
    }

    private void initialScreen(){
        // for clear all canvas that in pane
        pane.getChildren().clear();
        //draw all object
        background.draw();
        clouds.draw();
        bird.draw();
        describeGame.setText("-> Press key \"A\" to move monkey left.\n" +
                "-> Press key \"D\" to move monkey right.\n" +
                "-> When you get the position that you need, then click\n" +
                "     on mole's head that you want to hit it.\n" +
                "-> When the mole was hit. It will fade out, but the mole's \n" +
                "     resistance isn't exhausted the mole'll reappears.\n" +
                "-> So you have to hit it again but you have to wait for\n" +
                "     mole appear before you hit it again.\n" +
                "-> Do this until the mole is injured within 30 second.");

        // to show object on pane
        pane.getChildren().addAll(background, clouds, bird, monkey, describeBox, describeGame, howToPlay,  startGame, creator);

        cloudAnimation();
        animationBird();
    }

    private void cloudAnimation(){
        TranslateTransition cloud = new TranslateTransition(Duration.minutes(1),clouds);
        cloud.setFromX(clouds.getTranslateX());
        cloud.setToX(clouds.getTranslateX() + 1000);
        cloud.setCycleCount(Animation.INDEFINITE);
        cloud.setAutoReverse(true);
        cloud.play();
    }

    private void animationBird(){
        Path path = new Path();
        path.getElements().add(new MoveTo(750,50));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 380, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 50, 300, 250, 200));
        path.getElements().add(new CubicCurveTo(0, 200, 20, 100, -35, 150));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(20));
        pathTransition.setDelay(Duration.seconds(2.0));
        pathTransition.setNode(bird);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.play();
    }

}
