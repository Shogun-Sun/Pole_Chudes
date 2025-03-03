package org.example.pole_chudes.gamePageClasses;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ClickLetterAction {
    private static int result = 0;

    public ClickLetterAction(String letter, String word) {
        for(int i = 0; i<word.length(); i++) {
            if(String.valueOf(word.charAt(i)).equals(letter)) {
                List<Button> foundButtons = getButtonsByText(Word.word_buttons, letter);
                for (Button button : foundButtons) {
                    button.setStyle("-fx-text-fill: black;");
                }
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
        System.out.println(result);
        if (result == word.length()) {
            System.out.println("Вы победили!");
        }
    }
}
