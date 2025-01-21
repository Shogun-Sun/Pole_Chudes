package org.example.pole_chudes.gamePageClasses;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class LabelsLettersCreating {
    public List<Label> labelsLetter = new ArrayList<>();

    public LabelsLettersCreating(String letters, int squareSize, double spacing, Pane lettersPlace) {
        for (int i = 0; i < letters.length(); i++) {
            Label word_text = new Label(String.valueOf(letters.charAt(i)));
            word_text.setFont(new Font("Arial", 20));
            word_text.setStyle("-fx-border-color: black");
            word_text.setTextFill(Color.BLACK);
            word_text.setPrefWidth(squareSize);
            word_text.setPrefHeight(squareSize);
            word_text.setLayoutX(i * (squareSize + spacing));
            word_text.setLayoutY(0);
            word_text.setStyle("-fx-alignment: center;");

            lettersPlace.getChildren().add(word_text);
            labelsLetter.add(word_text);
        }
    }
    public List <Label> getLabelsLetter() {
        return labelsLetter;
    }
}
