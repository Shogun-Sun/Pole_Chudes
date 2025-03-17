package org.example.pole_chudes.gamePageClasses.wordLetters;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.example.pole_chudes.GamePageController;
import org.example.pole_chudes.gamePageClasses.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Letters {
    public static List<Button> letterButtons = new ArrayList<>();
    public static List<Boolean> state = new ArrayList<>();
    private static Timeline timeline;
    public Letters(GridPane lettersGrid, String word, GamePageController gamePageController) {
        Const constants = new Const();
        this.timeline = timeline;
        int col = 0;
        int row = 0;
        for (int i = 0; i < constants.letters.length(); i++) {
            Button button = new Button(String.valueOf(constants.letters.charAt(i)));
            button.setStyle("-fx-font-size: 12.6px");
            button.setDisable(true);
            lettersGrid.add(button, col, row);
            button.setOnAction(event -> {
                GamePageController.setWaitingForSpin(false);
                GamePageController.setWaitingForLetter(true);
                GamePageController.setWheelState(true);
                int buttonIndex = letterButtons.indexOf(button);
                state.set(buttonIndex, true);
                new ClickLetterAction(button.getText(), word, gamePageController);
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

    public void pressRandomButton(String word, GamePageController gamePageController) {
        Random random = new Random();
        List<Button> availableButtons = new ArrayList<>();

        for (int i = 0; i < letterButtons.size(); i++) {
            if (!state.get(i)) {
                availableButtons.add(letterButtons.get(i));
            }
        }

        if (!availableButtons.isEmpty()) {
            int randomIndex = random.nextInt(availableButtons.size());
            Button randomButton = availableButtons.get(randomIndex);

            GamePageController.setWheelState(false);
            state.set(letterButtons.indexOf(randomButton), true);
            new ClickLetterAction(randomButton.getText(), word, gamePageController);
            disableButtons();
        }
    }
}
