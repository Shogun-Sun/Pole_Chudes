package org.example.pole_chudes.gamePageClasses;

import javafx.scene.layout.Pane;
import org.example.pole_chudes.gamePageClasses.drum.AnimationManager;
import org.example.pole_chudes.gamePageClasses.drum.DrumElements;

public class UsesDependencies {

    private Pane wheelPane;
    private DrumElements drumElements;
    private Pane lettersPlace;
    private AnimationManager animationManager;

    public UsesDependencies(Pane wheelPane, DrumElements drumElements,
                            Pane lettersPlace) {

        this.wheelPane = wheelPane;
        this.drumElements = drumElements;
        this.lettersPlace = lettersPlace;
        this.animationManager = animationManager;
    }

    public Pane getWheelPane() {
        return wheelPane;
    }

    public DrumElements getDrumElements() {
        return drumElements;
    }

    public Pane getLettersPlace() {
        return lettersPlace;
    }

    public AnimationManager getAnimationManager() { return animationManager; }

    public void setAnimationManager(AnimationManager animationManager) {
        this.animationManager = animationManager;
    }
}
