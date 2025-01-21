package org.example.pole_chudes.gamePageClasses;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class LabelsWordLetterCreating {
    private List<Label> labelsWord = new ArrayList<>();

    public LabelsWordLetterCreating(String text, int squareSize, double spacing, Pane wordPlace) {
        for (int i = 0; i < text.length(); i++) {
            Label word_text = new Label(String.valueOf(text.charAt(i)));
            word_text.setFont(new Font("Arial", 20));
            word_text.setStyle("-fx-border-color: black");
            word_text.setTextFill(Color.BLACK);
            word_text.setPrefWidth(squareSize);
            word_text.setPrefHeight(squareSize);
            word_text.setLayoutX(i * (squareSize + spacing));
            word_text.setLayoutY(0);
            word_text.setStyle("-fx-alignment: center;");

            wordPlace.getChildren().add(word_text);
                labelsWord.add(word_text);
        }
    }

    public List<Label> getLabelsWord() {
        return labelsWord;
    }
}
