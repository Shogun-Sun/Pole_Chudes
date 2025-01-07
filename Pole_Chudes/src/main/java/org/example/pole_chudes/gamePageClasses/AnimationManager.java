package org.example.pole_chudes.gamePageClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

public class AnimationManager {
    public Timeline timeline;
    private double animationStart = 10;
    private final double animationEnd = 0;
    private final double stepAnimation = 2;
    private boolean isAnimationRunning = false;
    private List <String> sectorTexts;
    private Random random = new Random();

    public AnimationManager(
            Pane wheelPane,
            double anglePerSector,
            List <String> sectorTexts,
            DrumElements drumElements
    ) {
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
                        wheelPane.getChildren().add(drumElements.getCircleClick());

                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        drumElements.getCircleClick().setOnMouseClicked(event -> {
            if(!isAnimationRunning){
                wheelPane.getChildren().remove(drumElements.getCircleClick());
                animationStart = random.nextDouble(8, 14);
                timeline.play();
                isAnimationRunning = true;
            }
        });
    }
    private void stopDrum(Pane wheelPane, double anglePerSector) {
        timeline.stop();

        double rotation = wheelPane.getRotate() % 360;
//        if (rotation < 0) rotation += 360; //Если колесо крутиться против часовой

        double adjustedRotation = (360 - rotation) % 360;
        int sectorIndex = (int) (adjustedRotation / anglePerSector);

        String selectedValue = sectorTexts.get(sectorIndex);
        System.out.println("Значение сектора: " + selectedValue);

    }
}
