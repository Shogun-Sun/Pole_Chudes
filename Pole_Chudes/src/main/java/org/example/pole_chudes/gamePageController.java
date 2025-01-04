package org.example.pole_chudes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class gamePageController {
    @FXML
    private Pane drawingPane;

    @FXML
    public void initialize() {
        // Настройки барабана
        double centerX = 400;
        double centerY = 400;
        double radius = 100;
        int numSectors = 24;
        double anglePerSector = 360.0 / numSectors;

        // Черная обводка для барабана
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        // Создание панели для барабана
        Pane wheelPane = new Pane();
        wheelPane.setPrefSize(centerX * 2, centerY * 2);

        // Добавление круга на панель
        wheelPane.getChildren().add(circle);

        // Создание секторов
        for (int i = 0; i < numSectors; i++) {
            // Создание сектора
            Arc sector = new Arc(centerX, centerY, radius, radius, i * anglePerSector, anglePerSector);
            sector.setType(ArcType.ROUND);
            sector.setFill(i % 2 == 0 ? Color.BLUE : Color.WHITE);
            // Добавление сектора на панель
            wheelPane.getChildren().add(sector);
        }

        // Создание текста
        for (int i = 0; i < numSectors; i++) {
            // Создание текста
            Text text = new Text();
            text.setText("900");
            // Расчет координат для текста
            double angle = Math.toRadians(i * anglePerSector + anglePerSector / 2); // Центр сектора
            double textX = centerX + (radius * 0.9) * Math.cos(angle); // Позиция по X (ближе к краю сектора)
            double textY = centerY + (radius * 0.9) * Math.sin(angle); // Позиция по Y (ближе к краю сектора)

            text.setX(textX);
            text.setY(textY);

            // Центрирование текста относительно координат
            text.setTranslateX(-text.getBoundsInLocal().getWidth() / 2); // Центрирование по оси X
            text.setTranslateY(text.getBoundsInLocal().getHeight() / 4); // Центрирование по оси Y

            // Поворот текста, чтобы он располагался вдоль сектора
            text.setRotate(Math.toDegrees(angle) + 0); // Поворот текста вдоль сектора

            // Стиль для текста
            text.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");
            text.setFill(Color.BLACK); // Цвет текста

            // Добавление текста на панель
            wheelPane.getChildren().add(text);
        }

        // Добавление панели с барабаном на основную панель
        drawingPane.getChildren().add(wheelPane);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.05), event -> {
                    wheelPane.setRotate(wheelPane.getRotate() + 2); // Увеличиваем угол поворота
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Бесконечный цикл анимации
        timeline.play(); // Запуск анимации
    }

}
