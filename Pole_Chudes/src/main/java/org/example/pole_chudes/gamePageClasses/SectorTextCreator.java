package org.example.pole_chudes.gamePageClasses;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class SectorTextCreator {
    public SectorTextCreator(
            int numSectors,
            double anglePerSector,
            double centerX,
            double centerY,
            double radius,
            Pane whellPane,
            List<String> sectorTexts) {

        for (int i = 0; i < numSectors; i++) {
            double angle = Math.toRadians(i * anglePerSector + anglePerSector / 2);
            double textX = centerX + (radius * 0.8) * Math.cos(angle);
            double textY = centerY + (radius * 0.8) * Math.sin(angle);

            SectorText sectorText = new SectorText(
                    textX,
                    textY,
                    sectorTexts.get(i),
                    angle
            );
            whellPane.getChildren().add(sectorText.getText());
        }

    }
}
