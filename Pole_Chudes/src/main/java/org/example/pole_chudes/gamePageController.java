package org.example.pole_chudes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class gamePageController {
    @FXML
    private Pane drawingPane;

    @FXML
    public void initialize() {

        // Настройки барабана
        double centerX = 200;
        double centerY = 200;
        double radius = 100;
        int numSectors = 16;
        double anglePerSector = 360.0 / numSectors;

        //Черная обводка для барабана
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(Color.TRANSPARENT);  // Прозрачная внутренняя часть
        circle.setStroke(Color.BLACK);      // Черная обводка
        circle.setStrokeWidth(1);

        // Создание панели для вращения
        Pane wheelPane = new Pane();
        wheelPane.setPrefSize(centerX * 2, centerY * 2);


        wheelPane.getChildren().add(circle);

        // Создание секторов
        for (int i = 0; i < numSectors; i++) {
            Arc sector = new Arc(centerX, centerY, radius, radius, i * anglePerSector, anglePerSector);
            sector.setType(ArcType.ROUND);
            sector.setFill(i % 2 == 0 ? Color.BLUE : Color.WHITE);
            sector.setStroke(Color.BLACK);
            sector.setStrokeWidth(1);
            wheelPane.getChildren().add(sector);
        }

        drawingPane.getChildren().add(wheelPane);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.05), event -> {
                    wheelPane.setRotate(wheelPane.getRotate() + 2);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
