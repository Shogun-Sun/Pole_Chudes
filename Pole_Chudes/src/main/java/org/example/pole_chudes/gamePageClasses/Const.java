package org.example.pole_chudes.gamePageClasses;

import org.example.pole_chudes.MainPageController;

public class Const {
    public double centerX = MainPageController.currentStage.getWidth() / 2;
    public double centerY = MainPageController.currentStage.getHeight() / 2;
    public final double radius = 100;
    public final int numSectors = 24;
    public final double anglePerSector = 360.0 / numSectors;
}
