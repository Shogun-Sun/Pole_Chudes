package org.example.pole_chudes.gamePageClasses.wordLetters;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ClickLetterAction {
    public static int result = 0;
    private boolean letterGuessed = false;


    public ClickLetterAction(String letter, String word) {
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
            System.out.println("Вы победили!");
        }
    }

    public boolean isLetterGuessed() {
        return letterGuessed;
    }
}
