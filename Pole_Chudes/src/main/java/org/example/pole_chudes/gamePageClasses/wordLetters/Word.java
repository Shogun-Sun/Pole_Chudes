package org.example.pole_chudes.gamePageClasses.wordLetters;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.example.pole_chudes.GamePageController;

import java.util.ArrayList;
import java.util.List;

public class Word {
    public static Boolean onChosen = false;
    public static List<Button> word_buttons = new ArrayList<>();

    public Word(GridPane wordPlace, String word, GamePageController gamePageController) {
        int col = 0;
        int row = 0;
        for(int i = 0; i < word.length(); i++){
            Button button = new Button(String.valueOf(word.charAt(i)));
            button.setDisable(true);
            button.setStyle("-fx-opacity: 1; -fx-cursor: default; -fx-text-fill: transparent;");
            button.setUserData(word.charAt(i));
            button.setOnAction(event -> {
                gamePageController.setDialogText("Откройте букву " + button.getText());
                gamePageController.setWheelState(true);
                for(Button b : word_buttons){
                    if(b.getText().equals(button.getText())){
                        ClickLetterAction.result+=1;
                        b.setStyle("-fx-opacity: 1; -fx-cursor: default; -fx-text-fill: black;");

                    }
                }
                disableWordButtons();

            });
            wordPlace.add(button, col, row);
            col++;
            if (col > word.length()) {
                col = 0;
                row++;
            }
            word_buttons.add(button);
        }
    }

    public void enableWordButtons(){
        for(Button button : word_buttons){
            button.setDisable(false);
        }
    }

    public void disableWordButtons(){
        for(Button button : word_buttons){
            button.setDisable(true);
        }
    }
}
