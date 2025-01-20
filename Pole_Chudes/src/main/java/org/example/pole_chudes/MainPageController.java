package org.example.pole_chudes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainPageController {
    private double gameSceneX = 840;  //840
    private double gameSceneY = 540;    //540
    public static Stage currentStage;

    @FXML
    private Button startGame;

    @FXML
    private void startGame() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("gamePage.fxml"));
        Parent root = loader.load();

        Scene gameScene = new Scene(root, gameSceneX, gameSceneY);
        currentStage = (Stage) startGame.getScene().getWindow();
        currentStage.setScene(gameScene);
        currentStage.setResizable(false);
    }

    @FXML
    Button settings;

    @FXML
    private void settings() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("settingsPage.fxml"));

        Parent root = loader.load();

        Scene settingsScene = new Scene(root);
        Stage currentStage = (Stage) settings.getScene().getWindow();
        currentStage.setScene(settingsScene);
    }
}
