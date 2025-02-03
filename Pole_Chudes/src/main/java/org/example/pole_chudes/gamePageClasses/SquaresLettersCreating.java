package org.example.pole_chudes.gamePageClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SquaresLettersCreating {
    private int result;

    private List<Rectangle> squaresLetter = new ArrayList<>();
    public SquaresLettersCreating(UsesDependencies usesDependencies, String guesWord, Runnable onCurtainCreate) {
        Const aConst = new Const();
        onCurtainCreate.run();
        result = 0;
        for (int i = 0; i < aConst.letters.length(); i++) {
            String letter = String.valueOf(aConst.letters.charAt(i));

            Rectangle square = new Rectangle(aConst.squareSize, aConst.squareSize);
            square.setFill(Color.TRANSPARENT);
            square.setStroke(Color.BLACK);
            square.setX(i * (aConst.squareSize + aConst.spacing));
            square.setY(0);
            square.setOnMouseClicked(event -> {
                usesDependencies.getWheelPane().getChildren().add(usesDependencies.getDrumElements().getCircleClick());
                onCurtainCreate.run();
                if(guesWord.contains(letter)){
                System.out.println("Есть такая буква " + letter);
                for(int j = 0; j< guesWord.length(); j++){
                    if(String.valueOf(guesWord.charAt(j)).equals(letter)){
                        Rectangle squareToTransparent = usesDependencies.getSquaresWordCreating().getSquaresWord().get(j);
                         squareToTransparent.setFill(Color.TRANSPARENT);
                         result++;
                         if(result == guesWord.length()) {
                             System.out.println("Вы победили!");

                             for (int k = 0; k < usesDependencies.getLabelsLettersCreating().labelsLetter.size(); k++) {
                                 int finalK = k;
                                 Timeline timeline = new Timeline(new KeyFrame(Duration.millis((k + 1) * 50), even -> {
                                     usesDependencies.getLettersPlace().getChildren().removeAll(squaresLetter.get(finalK), usesDependencies.getLabelsLettersCreating().labelsLetter.get(finalK));
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
                    Label remLabel = usesDependencies.getLabelsLettersCreating().labelsLetter.get(index);
                    usesDependencies.getLettersPlace().getChildren().removeAll(square, remLabel);
                    squaresLetter.set(index, null);
                }
            });
            squaresLetter.add(square);
            usesDependencies.getLettersPlace().getChildren().add(square);
        }
    }
}
