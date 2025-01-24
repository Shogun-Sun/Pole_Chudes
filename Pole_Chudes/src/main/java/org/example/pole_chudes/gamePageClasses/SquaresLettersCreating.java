package org.example.pole_chudes.gamePageClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.print.attribute.standard.RequestingUserName;
import java.util.ArrayList;
import java.util.List;

public class SquaresLettersCreating {

    private int result;

    private List<Rectangle> squaresLetter = new ArrayList<>();
    public SquaresLettersCreating(String letters, String guesWord, int squareSize, double spacing, Pane lettersPlace, LabelsLettersCreating labelsLettersCreating, SquaresWordCreating squaresWordCreating, Pane curtainPlace, Runnable onCurtainCreate) {
        onCurtainCreate.run();
        result = 0;
        for (int i = 0; i < letters.length(); i++) {
            String letter = String.valueOf(letters.charAt(i));
            Rectangle square = new Rectangle(squareSize, squareSize);
            square.setFill(Color.TRANSPARENT);
            square.setStroke(Color.BLACK);
            square.setX(i * (squareSize + spacing));
            square.setY(0);
            square.setOnMouseClicked(event -> {
                onCurtainCreate.run();
                if(guesWord.contains(letter)){
                System.out.println("Есть такая буква " + letter);
                for(int j = 0; j< guesWord.length(); j++){
                    if(String.valueOf(guesWord.charAt(j)).equals(letter)){
                        Rectangle squareToTransparent = squaresWordCreating.getSquaresWord().get(j);
                         squareToTransparent.setFill(Color.TRANSPARENT);
                         result++;
                         if(result == guesWord.length()) {
                             System.out.println("Вы победили!");

                             for (int k = 0; k < labelsLettersCreating.labelsLetter.size(); k++) {
                                 int finalK = k;
                                 Timeline timeline = new Timeline(new KeyFrame(Duration.millis((k + 1) * 50), even -> {
                                     lettersPlace.getChildren().removeAll(squaresLetter.get(finalK), labelsLettersCreating.labelsLetter.get(finalK));
                                 }));
                                 timeline.setCycleCount(1);
                                 timeline.play();
                             }

                         }

                    }
                }

            } else{
                System.out.println("Нет такой буквы " + letter);
            }
                int index = squaresLetter.indexOf(square);
                if (index != -1) {
                    Label remLabel = labelsLettersCreating.labelsLetter.get(index);
                    lettersPlace.getChildren().removeAll(square, remLabel);
                }

            });

            squaresLetter.add(square);
            lettersPlace.getChildren().add(square);
        }
    }
}
