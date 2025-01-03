package org.example.pole_chudes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class mainPageController {
    @FXML
    private Button startGame;

    @FXML
    private void startGame() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("gamePage.fxml"));
        Parent root = loader.load();

        Scene gameScene = new Scene(root);
        Stage currentStage = (Stage) startGame.getScene().getWindow();
        currentStage.setScene(gameScene);
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
