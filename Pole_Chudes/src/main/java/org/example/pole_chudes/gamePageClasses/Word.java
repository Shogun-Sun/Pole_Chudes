package org.example.pole_chudes.gamePageClasses;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class Word {
    public static List<Button> word_buttons = new ArrayList<>();

    public Word(GridPane wordPlace, String word ) {
        int col = 0;
        int row = 0;;
        for(int i = 0; i < word.length(); i++){
            Button button = new Button(String.valueOf(word.charAt(i)));
            button.setStyle("-fx-opacity: 1; -fx-cursor: default; -fx-text-fill: transparent;");
            button.setUserData(word.charAt(i));
            wordPlace.add(button, col, row);
            col++;
            if (col > word.length()) {
                col = 0;
                row++;
            }
            word_buttons.add(button);
        }
    }
}
