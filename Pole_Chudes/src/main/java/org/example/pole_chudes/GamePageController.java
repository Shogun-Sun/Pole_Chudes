package org.example.pole_chudes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import org.example.pole_chudes.gamePageClasses.*;

import java.util.List;

public class GamePageController {
    @FXML
    private Pane drum;

    @FXML
    private Label pla1;

    @FXML
    private Label pla2;

    @FXML
    private Label pla3;

    @FXML
    private Label word;

    @FXML
    private Label definition;

    private int score = 0;
    private String value;

    // Настройки барабана
    private final double centerX = 200;
    private final double centerY = 200;
    private final double radius = 100;
    private final int numSectors = 24;
    private final double anglePerSector = 360.0 / numSectors;

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
        DrumElements drumElements = new DrumElements(centerX, centerY, radius);

        WordDefinitionManager wordDefinitionManager = new WordDefinitionManager();
        word.setText(wordDefinitionManager.getWord());

        definition.setPrefWidth(500);
        definition.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        definition.setText(wordDefinitionManager.getDefinition());
        definition.setWrapText(true);

        // Панель для барабана
        Pane wheelPane = new Pane();
        wheelPane.setPrefSize(centerX * 2, centerY * 2);

        Group drumGroup = new Group();
        drumGroup.getChildren().addAll(wheelPane, drumElements.getArrowLine(), drumElements.getArrowHead());

        SectorsCreating sectorsCreating = new SectorsCreating(numSectors, centerX, centerY, radius, anglePerSector, wheelPane);
        wheelPane.getChildren().addAll(drumElements.getCircleBorder(), drumElements.getCircleClick());

        SectorTextCreator sectorTextCreator = new SectorTextCreator(numSectors, anglePerSector, centerX, centerY, radius, wheelPane, sectorTexts);

        drumGroup.setLayoutX(50);
        drumGroup.setLayoutY(50);
        drum.getChildren().add(drumGroup);

        AnimationManager animationManager = new AnimationManager(wheelPane, anglePerSector, sectorTexts, drumElements);
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
    }
}
