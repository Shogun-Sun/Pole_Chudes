package org.example.pole_chudes.gamePageClasses.wordLetters;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.util.Duration;
import org.example.pole_chudes.GamePageController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClickLetterAction {
    public static int result = 0;
    private boolean letterGuessed = false;
    private String[] dialogs = {"Откройте букву <<%s>>", "Вы абсолютно правы! Откройте букву <<%s>>", "К сожалению вы ошиблись, в слове нет буквы <<%s>>", "И у нас есть победитель!"};
    private Random random = new Random();
    private GamePageController gamePageController;

    public ClickLetterAction(String letter, String word, GamePageController gamePageController) {
        this.gamePageController = gamePageController;
        for(int i = 0; i<word.length(); i++) {
            if(String.valueOf(word.charAt(i)).equals(letter)) {
                List<Button> foundButtons = getButtonsByText(Word.word_buttons, letter);
                for (Button button : foundButtons) {
                    button.setStyle("-fx-opacity: 1; -fx-cursor: default; -fx-text-fill: black;");
                }
                letterGuessed = true;
                result++;
            }
        }
        if(letterGuessed){
            int index = random.nextInt(2);
            gamePageController.setDialogText(String.format(dialogs[index], letter));
        } else{
            gamePageController.switchTurn();
            gamePageController.setDialogText(String.format(dialogs[2], letter));
            if (GamePageController.currentState == GamePageController.GameState.PLAYER) {
                GamePageController.setWheelState(true);
            } else if (GamePageController.currentState == GamePageController.GameState.BOT2) {
                GamePageController.setWheelState(true);
            }

            GamePageController.setWaitingForLetter(false);
            GamePageController.setWaitingForSpin(false);
        }
        checkWin(result, word);
    }

    private List<Button> getButtonsByText(List<Button> buttons, String targetLetter) {
        List<Button> foundButtons = new ArrayList<>();
        for (Button button : buttons) {
            if (button.getText().equals(targetLetter)) {
                foundButtons.add(button);

            }
        }
        return foundButtons;
    }

    public void checkWin(int result, String word){
        if (result == word.length()) {
            for (int i = 0; i < Letters.letterButtons.size(); i++) {
                Letters.letterButtons.get(i).setDisable(true);
            }
        GamePageController.setWheelState(false);
        gamePageController.setDialogText(dialogs[3]);

            Timeline exitTimeline = new Timeline(new KeyFrame(Duration.seconds(7), event -> {
                Platform.exit();
            }));
            exitTimeline.play();
        }
    }
}
