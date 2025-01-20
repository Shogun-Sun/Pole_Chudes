package org.example.pole_chudes.customcomponents;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class WordPanel extends Pane {

    private List<Rectangle> squares = new ArrayList<>();

    public WordPanel(String word, boolean answer) {
        createSquares(word, answer);
    }

    private void createSquares(String word, boolean answer) {
        this.getChildren().clear();
        squares.clear();

        if(answer) {
            int squareSize = 30;
            int spacing = 10;
            createText(word, squareSize, spacing);
            createSquare(word, squareSize, spacing, Color.BLUE, true);
        } else{
            int squareSize = 20;
            double spacing = 5.5;
            createText(word, squareSize, spacing);
            createSquare(word, squareSize, spacing, Color.TRANSPARENT, false);
        }
    }
    private void createText(String word, int squareSize, double spacing) {
        for (int i = 0; i < word.length(); i++) {
            Label word_text = new Label(String.valueOf(word.charAt(i)));
            word_text.setFont(new Font("Arial", 20));
            word_text.setStyle("-fx-border-color: black");
            word_text.setTextFill(Color.BLACK);
            word_text.setPrefWidth(squareSize);
            word_text.setPrefHeight(squareSize);
            word_text.setLayoutX(i * (squareSize + spacing));
            word_text.setLayoutY(0);
            word_text.setStyle("-fx-alignment: center;");

            this.getChildren().add(word_text);
        }
    }
    private void createSquare(String word, int squareSize, double spacing, Color color, boolean varSquare) {
        for (int i = 0; i < word.length(); i++) {
            String text = String.valueOf(word.charAt(i));
            Rectangle square = new Rectangle(squareSize, squareSize);
            square.setFill(color);
            square.setStyle("-fx-background-color: blue");
            square.setStroke(Color.BLACK);
            square.setX(i * (squareSize + spacing));
            square.setY(0);

            if(varSquare == true){
                clickWordSquare(square, i);
            } else{
                clickLetterSquare(square, text);
            }



            this.getChildren().add(square);
            squares.add(square);
        }
    }

    private void clickWordSquare(Rectangle square, int finalI) {
        square.setOnMouseClicked(event -> {
            System.out.println("Клик на квадрате " + finalI);
            square.setFill(Color.TRANSPARENT);
        });
    }

    private void clickLetterSquare(Rectangle square, String letter) {
        square.setOnMouseClicked(event -> {
            System.out.println("Клик на квадрате " + letter);
            square.setFill(Color.TRANSPARENT);
        });
    }

    public List<Rectangle> getSquares() {
        return squares;
    }
}
