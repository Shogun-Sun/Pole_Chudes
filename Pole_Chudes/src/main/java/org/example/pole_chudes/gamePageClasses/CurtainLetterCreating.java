package org.example.pole_chudes.gamePageClasses;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CurtainLetterCreating {
    Rectangle rectangle = new Rectangle();
    public CurtainLetterCreating(Pane curtanePane) {
        rectangle.setFill(Color.WHITE);

        curtanePane.getChildren().add(rectangle);

    }
}
