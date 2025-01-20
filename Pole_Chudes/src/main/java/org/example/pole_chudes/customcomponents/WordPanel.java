package org.example.pole_chudes.customcomponents;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class WordPanel extends Pane {

    private List<Rectangle> squaresWord = new ArrayList<>();
    private List<Rectangle> squaresLetter = new ArrayList<>();

    private List<Label> labelsWord = new ArrayList<>();
    private List<Label> labelsLetter = new ArrayList<>();


    private String guesWord;

    public WordPanel(String word, String letters, boolean answer) {
        createSquares(word, letters, answer);


    }

    private void createSquares(String word, String letters, boolean answer) {
        //this.getChildren().clear();
            guesWord = word;
            if (answer) {
                int squareSize = 30;
                int spacing = 8;
                createText(word, squareSize, spacing, true);
                createSquareWord(word, squareSize, spacing);
            } else {
                int squareSize = 20;
                double spacing = 5.5;
                createText(letters, squareSize, spacing, false );
                createSquareLetter(letters, squareSize, spacing);
            }
    }
    private void createText(String text, int squareSize, double spacing, boolean varLabel) {
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

            this.getChildren().add(word_text);

            if(varLabel){
                labelsWord.add(word_text);
            } else{
                labelsLetter.add(word_text);
            }

        }
    }
    private void createSquareWord(String text, int squareSize, double spacing) {
        for (int i = 0; i < text.length(); i++) {
            Rectangle square = new Rectangle(squareSize, squareSize);
            square.setFill(Color.BLUE);
            square.setStroke(Color.BLACK);
            square.setX(i * (squareSize + spacing));
            square.setY(0);

                squaresWord.add(square);
                clickWordSquare(square);


            this.getChildren().add(square);
        }
        System.out.println("Final squaresWord size: " + squaresWord.size());
    }

    private void createSquareLetter(String text, int squareSize, double spacing) {
        for (int i = 0; i < text.length(); i++) {
            String letters = String.valueOf(text.charAt(i));

            Rectangle square = new Rectangle(squareSize, squareSize);
            square.setFill(Color.TRANSPARENT);
            square.setStroke(Color.BLACK);
            square.setX(i * (squareSize + spacing));
            square.setY(0);

                squaresLetter.add(square);
                clickLetterSquare(square, letters);

            this.getChildren().add(square);
        }
        System.out.println("Final squaresLetter size: " + squaresLetter.size());
    }

    private void clickWordSquare(Rectangle square) {
        square.setOnMouseClicked(event -> {
            square.setFill(Color.TRANSPARENT);
        });
    }

    private void clickLetterSquare(Rectangle square, String letter) {
        square.setOnMouseClicked(event -> {
            System.out.println("Клик на квадрате " + letter);
            if(guesWord.contains(letter)){
                System.out.println("Есть такая буква " + letter);
//                for(int i = 0; i< guesWord.length(); i++){
//                    if(String.valueOf(guesWord.charAt(i)).equals(letter)){
//                        Rectangle squareToTransparent = squaresWord.get(i);
//                        squareToTransparent.setFill(Color.TRANSPARENT);
//                    }
//                }

            } else{
                System.out.println("Нет такой буквы " + letter);
            }
            int index = squaresLetter.indexOf(square);
            if (index != -1) {
                Label remLabel = labelsLetter.get(index);
                this.getChildren().removeAll(square, remLabel);
            }
        });
    }
    public void checkList(){
        System.out.println(squaresWord.size());
        System.out.println(squaresLetter.size());
    }
}
