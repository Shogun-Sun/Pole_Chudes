package org.example.pole_chudes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import org.example.pole_chudes.gamePageClasses.DrumElements;
import org.example.pole_chudes.gamePageClasses.SectorTextCreator;
import org.example.pole_chudes.gamePageClasses.SectorsCreating;

import java.util.*;
import java.util.List;

public class gamePageController {
    @FXML
    private Pane drum;

    @FXML
    private Pane arrow;

    public Timeline timeline;
   // private List<String> sectorTexts = new ArrayList<>();

    private List<String> sectorTexts = List.of(
            "100", "200", "Б", "500", "900", "1000", "Б", "300",
            "400", "П", "800", "700", "600", "Б", "Б", "Ш",
            "100", "Ш", "Ш", "200", "300", "Б", "Б", "500"
    );

    private double animationStart = 10;
    private final double animationEnd = 0;
    private double stepAnimation = 2;
    private boolean isAnimationRunning = false;
    private Random random = new Random();

    @FXML
    public void initialize() {

        // Настройки барабана
        double centerX = 200;
        double centerY = 200;
        double radius = 100;
        int numSectors = 24;
        double anglePerSector = 360.0 / numSectors;

        DrumElements drumElements = new DrumElements(centerX, centerY, radius);

        // Панель для барабана
        Pane wheelPane = new Pane();
        wheelPane.setPrefSize(centerX * 2, centerY * 2);

        Group drumGroup = new Group();
        drumGroup.getChildren().addAll(wheelPane, drumElements.getArrowLine(), drumElements.getArrowHead());

        SectorsCreating sectorsCreating = new SectorsCreating(numSectors, centerX, centerY, radius, anglePerSector, wheelPane);
        wheelPane.getChildren().addAll(drumElements.getCircleBorder(), drumElements.getCircleClick());

        SectorTextCreator sectorTextCreator = new SectorTextCreator(numSectors, anglePerSector, centerX, centerY, radius, wheelPane, sectorTexts);

        drumGroup.setLayoutX(50);
        drumGroup.setLayoutY(50);
        drum.getChildren().add(drumGroup);

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
