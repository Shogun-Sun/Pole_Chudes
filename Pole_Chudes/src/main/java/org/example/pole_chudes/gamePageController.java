package org.example.pole_chudes;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import org.example.pole_chudes.gamePageClasses.AnimationManager;
import org.example.pole_chudes.gamePageClasses.DrumElements;
import org.example.pole_chudes.gamePageClasses.SectorTextCreator;
import org.example.pole_chudes.gamePageClasses.SectorsCreating;

import java.util.*;
import java.util.List;

public class gamePageController {
    @FXML
    private Pane drum;

    private List<String> sectorTexts = List.of(
            "100", "200", "Б", "500", "900", "1000", "Б", "300",
            "400", "П", "800", "700", "600", "Б", "Б", "Ш",
            "100", "Ш", "Ш", "200", "300", "Б", "Б", "500"
    );

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

        AnimationManager animationManager = new AnimationManager(wheelPane, anglePerSector, sectorTexts, drumElements);
    }
}
