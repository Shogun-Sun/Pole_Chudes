package org.example.pole_chudes;

import com.sun.tools.javac.Main;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.pole_chudes.gamePageClasses.*;

import javax.swing.plaf.synth.ColorType;
import java.util.List;

public class GamePageController {
    @FXML
    private Pane wheelPane;

    @FXML
    private Label pla1;

    @FXML
    private Label pla2;

    @FXML
    private Label pla3;

    @FXML
    private Pane word;

    @FXML
    private Label definition;

    private int score = 0;
    private String value;

    private Rectangle square;
    private Rectangle hideSquare;

    static int scorepla1 = 0;
    static int scorepla2 = 0;
    static int scorepla3 = 0;

    private List<String> sectorTexts = List.of(
            "100", "200", "Б", "500", "900", "1000", "Б", "300",
            "400", "П", "800", "700", "600", "Б", "Б", "Ш",
            "100", "Ш", "Ш", "200", "300", "Б", "Б", "500"
    );

    @FXML
    public void initialize() {
        PauseTransition pause = new PauseTransition(Duration.millis(1));
        pause.setOnFinished(event -> {
            Const constants = new Const();
            DrumElements drumElements = new DrumElements(constants.centerX, constants.centerY, constants.radius);

            WordDefinitionManager wordDefinitionManager = new WordDefinitionManager();

            //word.setText(wordDefinitionManager.getWord());
            square = new Rectangle(500, 500, 50, 50);
            square.setFill(Color.RED);
            square.setStroke(Color.BLACK);

            definition.setPrefWidth(500);
            definition.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            definition.setText(wordDefinitionManager.getDefinition());
            definition.setWrapText(true);

            Pane drumPane = new Pane();
            drumPane.setPrefSize(constants.centerX * 2, constants.centerY * 2);

            Group drumGroup = new Group();
            drumGroup.getChildren().addAll(drumPane, drumElements.getArrowLine(), drumElements.getArrowHead());

            new SectorsCreating(constants.numSectors, constants.centerX, constants.centerY, constants.radius, constants.anglePerSector, drumPane);
            drumPane.getChildren().addAll(drumElements.getCircleBorder(), drumElements.getCircleClick());

            new SectorTextCreator(constants.numSectors, constants.anglePerSector, constants.centerX, constants.centerY, constants.radius, drumPane, sectorTexts);

            wheelPane.getChildren().add(drumGroup);

            AnimationManager animationManager = new AnimationManager(drumPane, constants.anglePerSector, sectorTexts, drumElements);
            animationManager.setOnAnimationEnd(() -> {
                try{
                    score = Integer.parseInt(animationManager.getSelectedValue());
                } catch (NumberFormatException e){
                    value = animationManager.getSelectedValue();
                }

                if(value == null){
                    value = "";
                }

                switch (value){
                    case "Б":
                        scorepla1 = 0;
                        score = 0;
                        pla1.setText("0");
                        value = "";
                        break;

                    default:
                        scorepla1 += score;
                        pla1.setText(String.valueOf(scorepla1));
                        break;

                }
            });
        });
        pause.play();
    }
}
