package org.example.pole_chudes.gamePageClasses;

import org.example.pole_chudes.MainPageController;

import java.util.List;

public class Const {
//    public final double centerX = MainPageController.currentStage.getWidth() / 2;
//    public final double centerY = MainPageController.currentStage.getHeight() / 2;

    public final double centerX = 840 / 2;
    public final double centerY = 540 / 2;
    public final double radius = 100;
    public final int numSectors = 24;
    public final double anglePerSector = 360.0 / numSectors;

    public final int squareSize = 20;
    public final double spacing = 5.5;

    public final List<String> sectorTexts = List.of(
            "100", "\uD83D\uDDDD", "Б", "500", "☎", "1000", "x2", "300",
            "400", "П", "0", "+", "600", "700", "Б", "550",
            "50", "☎", "+", "450", "250", "350", "150", "650"
    );
    public final String letters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

}
