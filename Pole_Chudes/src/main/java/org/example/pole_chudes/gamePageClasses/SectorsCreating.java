package org.example.pole_chudes.gamePageClasses;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class SectorsCreating {
    public SectorsCreating(
            int numSectors,
            double centerX,
            double centerY,
            double radius,
            double anglePerSector,
            Pane whellPane) {
        for (int i = 0; i < numSectors; i++) {
            Arc sector = new Arc(centerX, centerY, radius, radius, i * anglePerSector, anglePerSector);
            sector.setType(ArcType.ROUND);
            sector.setFill(i % 2 == 0 ? Color.BLUE : Color.WHITE);

            whellPane.getChildren().add(sector);
        }
    }
}
