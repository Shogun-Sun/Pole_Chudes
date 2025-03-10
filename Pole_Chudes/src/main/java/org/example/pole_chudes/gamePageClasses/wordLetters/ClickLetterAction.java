package org.example.pole_chudes.gamePageClasses.wordLetters;

import javafx.scene.control.Button;
import org.example.pole_chudes.GamePageController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClickLetterAction {
    public static int result = 0;
    private boolean letterGuessed = false;
    private String[] dialogs = {"Откройте букву %s", "Вы абсолютно правы! Откройте букву %s", "К сожалению вы ошиблись, в слове нет буквы %s"};
    private Random random = new Random();

    public ClickLetterAction(String letter, String word, GamePageController gamePageController) {
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
            gamePageController.setDialogText(String.format(dialogs[2], letter));
            gamePageController.circleClickDisable();
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

    private void checkWin(int result, String word){
        if (result == word.length()) {
            for (int i = 0; i < Letters.letterButtons.size(); i++) {
                Letters.letterButtons.get(i).setDisable(true);
            }

        }
    }

    public boolean isLetterGuessed() {
        return letterGuessed;
    }
}
