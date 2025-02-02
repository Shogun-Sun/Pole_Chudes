package org.example.pole_chudes.gamePageClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class AnimationManager {

    private Runnable onAnimationEnd;

    private Timeline timeline;
    private double animationStart = 10;
    private final double animationEnd = 0;
    private final double stepAnimation = 2;
    private boolean isAnimationRunning = false;

    private List <String> sectorTexts;
    private Random random = new Random();

    private String selectedValue = "";

    public boolean curtainStatus;

    public AnimationManager(
            Pane wheelPane,
            double anglePerSector,
            List <String> sectorTexts,
            DrumElements drumElements,
            Runnable onAnimationEnd
    ) {
        this.onAnimationEnd = onAnimationEnd;

        wheelPane.setRotate(0);
        this.sectorTexts = sectorTexts;
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> {
                    wheelPane.setRotate(wheelPane.getRotate() + animationStart);

                    if (animationStart > animationEnd) {
                        animationStart -= stepAnimation * 0.01;
                    }
                    if (animationStart <= animationEnd) {
                        stopDrum(wheelPane, anglePerSector);
                        isAnimationRunning = false;
                        if (onAnimationEnd != null) {
                            onAnimationEnd.run();
                        }

                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        drumElements.getCircleClick().setOnMouseClicked(event -> {
            if(!isAnimationRunning){
                wheelPane.getChildren().remove(drumElements.getCircleClick());
                animationStart = random.nextDouble(8, 10);
                timeline.play();
                isAnimationRunning = true;
            }
        });

        drumElements.getCircleClick().setFocusTraversable(true);
        drumElements.getCircleClick().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if (!isAnimationRunning) {
                    wheelPane.getChildren().remove(drumElements.getCircleClick());
                    animationStart = random.nextDouble(8, 14);
                    timeline.play();
                    isAnimationRunning = true;
                }
            }
        });
    }
    private void stopDrum(Pane wheelPane, double anglePerSector) {
        curtainStatus = false;
        timeline.stop();

        double rotation = wheelPane.getRotate() % 360;
//        if (rotation < 0) rotation += 360; //Если колесо крутиться против часовой

        double adjustedRotation = (263 - rotation) % 360;
        if (adjustedRotation < 0) {
            adjustedRotation += 360;
        }
        int sectorIndex = (int) Math.round(adjustedRotation / anglePerSector);
        if(sectorIndex == 24){
            sectorIndex = 0;
        }
        selectedValue = sectorTexts.get(sectorIndex);

        if (onAnimationEnd != null) {
            onAnimationEnd.run();
        }
    }

    public void setOnAnimationEnd(Runnable onAnimationEnd) {
        this.onAnimationEnd = onAnimationEnd;
    }

    public String getSelectedValue(){
        return selectedValue;
    }

    public boolean getCurtainStatus(){
        return curtainStatus;
    }
}
