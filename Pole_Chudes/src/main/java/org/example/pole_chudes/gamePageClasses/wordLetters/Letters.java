package org.example.pole_chudes.gamePageClasses.wordLetters;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.example.pole_chudes.gamePageClasses.Const;

import java.util.ArrayList;
import java.util.List;

public class Letters {
    public static List<Button> letterButtons = new ArrayList<>();
    public static List<Boolean> state = new ArrayList<>();
    public Letters(GridPane lettersGrid, String word) {
        Const constants = new Const();

        int col = 0;
        int row = 0;
        for (int i = 0; i < constants.letters.length(); i++) {
            Button button = new Button(String.valueOf(constants.letters.charAt(i)));
            button.setStyle("-fx-font-size: 12.6px");
            button.setDisable(true);
            lettersGrid.add(button, col, row);
            button.setOnAction(event -> {
                int buttonIndex = letterButtons.indexOf(button);
                state.set(buttonIndex, true);
                new ClickLetterAction(button.getText(), word);
                disableButtons();
            });
            col++;
            if (col > 32) {
                col = 0;
                row++;
            }
            letterButtons.add(button);
            state.add(false);
        }
    }

    public void enableButtons() {
        for (int i = 0; i < letterButtons.size(); i++) {
            Button button = letterButtons.get(i);
            boolean buttonState = state.get(i);
            if (!buttonState) {
                button.setDisable(false);
            }
        }
    }

    public void disableButtons(){
        for (Button button : letterButtons) {
            button.setDisable(true);
        }
    }
}
