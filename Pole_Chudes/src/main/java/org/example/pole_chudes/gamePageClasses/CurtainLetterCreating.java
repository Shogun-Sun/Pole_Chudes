package org.example.pole_chudes.gamePageClasses;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CurtainLetterCreating {
    Rectangle rectangle = new Rectangle();
    public CurtainLetterCreating() {
        rectangle = new Rectangle(840, 30);
        rectangle.setFill(Color.TRANSPARENT);
    }

    public void CreateCurtain(Pane curtanePane) {
        curtanePane.getChildren().add(rectangle);
    }

    public void RemoveCurtain(Pane curtanePane) {
        curtanePane.getChildren().remove(rectangle);

    }
}
