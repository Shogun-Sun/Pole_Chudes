package org.example.pole_chudes.gamePageClasses;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class DrumElements {
    private Circle circleBorder;
    private Circle circleClick;
    private Line arrowLine;
    private Polygon arrowHead;
    public DrumElements(
            double centerX,
            double centerY,
            double radius
    ) {
        Const consts = new Const();
        // Черная обводка для барабана
        circleBorder = new Circle(centerX, centerY, radius);
        circleBorder.setFill(Color.TRANSPARENT);
        circleBorder.setStroke(Color.BLACK);

        // Область клика
        circleClick = new Circle(centerX, centerY, radius);
        circleClick.setFill(Color.TRANSPARENT);
        // Стрелка
        arrowLine = new Line(consts.centerX, consts.centerY, consts.centerX, consts.centerY-40);
        arrowLine.setStroke(Color.BLACK);
        arrowLine.setStrokeWidth(2);
        arrowHead = new Polygon();
        arrowHead.getPoints().addAll(
                consts.centerX, consts.centerY-60,
                consts.centerX-10, consts.centerY-40,
                consts.centerX+10, consts.centerY-40
        );
        arrowHead.setFill(Color.BLACK);
    }
    public Circle getCircleBorder() {
        return circleBorder;
    }

    public Circle getCircleClick() {
        return circleClick;
    }

    public Line getArrowLine() {
        return arrowLine;
    }

    public Polygon getArrowHead() {
        return arrowHead;
    }

}
