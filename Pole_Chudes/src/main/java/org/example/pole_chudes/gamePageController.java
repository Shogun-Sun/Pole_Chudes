package org.example.pole_chudes;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class gamePageController {
    @FXML
    private Pane drum;

    public Timeline timeline;
    private List<String> sectorValues;

    @FXML
    public void initialize() {

        // Настройки барабана
        double centerX = 200;
        double centerY = 200;
        double radius = 100;
        int numSectors = 24;
        double anglePerSector = 360.0 / numSectors;

        // Черная обводка для барабана
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        //Стрелка
        Line line = new Line(310, 200, 330, 200);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        Polygon arrowHead = new Polygon();
        arrowHead.getPoints().addAll(
                290.0, 200.0,
                310.0, 210.0,
                310.0, 190.0
        );
        arrowHead.setFill(Color.BLACK);

        // Создание панели для барабана
        Pane wheelPane = new Pane();
        wheelPane.setPrefSize(centerX * 2, centerY * 2);

        Group drumGroup = new Group();
        drumGroup.getChildren().addAll(wheelPane, line, arrowHead);

        sectorValues = List.of(
                "100", "200", "Б", "500", "900", "1000", "Б", "300",
                "400", "П", "800", "700", "600", "Б", "Б", "Ш",
                "100", "Ш", "Ш", "200", "300", "Б", "Б", "500"
        );

        // Создание секторов
        for (int i = 0; i < numSectors; i++) {
            Arc sector = new Arc(centerX, centerY, radius, radius, i * anglePerSector, anglePerSector);
            sector.setType(ArcType.ROUND);
            sector.setFill(i % 2 == 0 ? Color.BLUE : Color.WHITE);

            wheelPane.getChildren().add(sector);
        }

        // Создание текста
        for (int i = 0; i < numSectors; i++) {
            Text text = new Text();
            text.setText(sectorValues.get(i));
            double angle = Math.toRadians(i * anglePerSector + anglePerSector / 2);
            double textX = centerX + (radius * 0.8) * Math.cos(angle);
            double textY = centerY + (radius * 0.8) * Math.sin(angle);

            text.setX(textX);
            text.setY(textY);

            text.setTranslateX(-text.getBoundsInLocal().getWidth() / 2);
            text.setTranslateY(text.getBoundsInLocal().getHeight() / 4);
            text.setRotate(Math.toDegrees(angle) + 0); //поворот текста

            text.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");
            text.setFill(Color.BLACK);

            wheelPane.getChildren().add(text);
        }
        wheelPane.getChildren().add(circle);

        // Добавление панели с барабаном и стрелки на основную панель
        drumGroup.setLayoutX(100);
        drumGroup.setLayoutY(200);
        drum.getChildren().add(drumGroup);

        // Анимация вращения барабана
        timeline = new Timeline(

                new KeyFrame(Duration.seconds(0.01), event -> {
                    wheelPane.setRotate(wheelPane.getRotate() + 2);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        circle.setOnMouseClicked(event -> {
            timeline.play();
        });


        stopDrum(wheelPane, numSectors, anglePerSector);
    }
    private void stopDrum(Pane wheelPane, int numSecors, double anglePerSector) {
        timeline.stop();

        double rotation = wheelPane.getRotate() % 360;
//        if (rotation < 0) rotation += 360; //Если колесо крутиться против часовой

        double adjustedRotation = (360 - rotation) % 360;
        int sectorIndex = (int) (adjustedRotation / anglePerSector);

        String selectedValue = sectorValues.get(sectorIndex);
        System.out.println("Значение сектора: " + selectedValue);

    }
}
