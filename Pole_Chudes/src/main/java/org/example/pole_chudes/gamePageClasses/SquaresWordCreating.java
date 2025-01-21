package org.example.pole_chudes.gamePageClasses;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class SquaresWordCreating {
    public List<Rectangle> squaresWord = new ArrayList<>();

    public SquaresWordCreating(String text, int squareSize, double spacing, Pane wordPlace) {
        for (int i = 0; i < text.length(); i++) {
            Rectangle square = new Rectangle(squareSize, squareSize);
            square.setFill(Color.BLUE);
            square.setStroke(Color.BLACK);
            square.setX(i * (squareSize + spacing));
            square.setY(0);

            square.setOnMouseClicked(event -> {
                square.setFill(Color.TRANSPARENT);
            });

            squaresWord.add(square);
            wordPlace.getChildren().add(square);
        }
    }

    public List<Rectangle> getSquaresWord() {
        return squaresWord;
    }
}
