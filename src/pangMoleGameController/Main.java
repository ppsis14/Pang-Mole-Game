package pangMoleGameController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/pangMoleGameView/introPangMoleGame.fxml"));
        primaryStage.setTitle("Pang Mole Game - 5910401033");
        primaryStage.setScene(new Scene(root, 690, 590));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
