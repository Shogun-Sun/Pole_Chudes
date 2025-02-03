package org.example.pole_chudes.gamePageClasses;

import javafx.scene.layout.Pane;

public class UsesDependencies {

    private Pane wheelPane;
    private DrumElements drumElements;
    private LabelsLettersCreating labelsLettersCreating;
    private SquaresWordCreating squaresWordCreating;
    private Pane lettersPlace;

    public UsesDependencies(Pane wheelPane, DrumElements drumElements,
                            LabelsLettersCreating labelsLettersCreating, SquaresWordCreating squaresWordCreating,
                            Pane lettersPlace) {

        this.wheelPane = wheelPane;
        this.drumElements = drumElements;
        this.labelsLettersCreating = labelsLettersCreating;
        this.squaresWordCreating = squaresWordCreating;
        this.lettersPlace = lettersPlace;
    }

    public Pane getWheelPane() {
        return wheelPane;
    }

    public DrumElements getDrumElements() {
        return drumElements;
    }

    public LabelsLettersCreating getLabelsLettersCreating() {
        return labelsLettersCreating;
    }

    public SquaresWordCreating getSquaresWordCreating() {
        return squaresWordCreating;
    }

    public Pane getLettersPlace() {
        return lettersPlace;
    }
}
