package org.example.pole_chudes;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.example.pole_chudes.gamePageClasses.*;
import java.util.List;


public class GamePageController {
    @FXML
    private Pane wheelPane;

    @FXML
    private Pane wordPlace;

    @FXML
    private Pane lettersPlace;

    @FXML
    private Pane definitionContainer;

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

    private String letter = "а";

    private Rectangle square;
    private Rectangle hideSquare;

    static int scorepla1 = 0;
    static int scorepla2 = 0;
    static int scorepla3 = 0;

    private List<String> sectorTexts = List.of(
            "100", "200", "Б", "500", "900", "1000", "Б", "300",
            "400", "П", "800", "700", "600", "Б", "Б", "Ш",
            "1200", "Ш", "Ш", "200", "1300", "Б", "Б", "1500"
    );

    public String letters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    @FXML
    public void initialize() {
//        PauseTransition pause = new PauseTransition(Duration.millis(1));
//        pause.setOnFinished(event -> {
            Const constants = new Const();
            DrumElements drumElements = new DrumElements(constants.centerX, constants.centerY, constants.radius);

            WordDefinitionManager wordDefinitionManager = new WordDefinitionManager();

            //word.setText(wordDefinitionManager.getWord());

        //Поле для загаданного слова
        LabelsWordLetterCreating labelsWordLetterCreating = new LabelsWordLetterCreating(wordDefinitionManager.getWord(), 30, 8, wordPlace);
        SquaresWordCreating squaresWordCreating = new SquaresWordCreating(wordDefinitionManager.getWord(), 30, 8, wordPlace);

        //Панель для выбора буквы
        LabelsLettersCreating labelsLettersCreating = new LabelsLettersCreating(letters, 20, 5.5, lettersPlace);
        SquaresLettersCreating squaresLettersCreating = new SquaresLettersCreating(letters, wordDefinitionManager.getWord(), 20, 5.5, lettersPlace, labelsLettersCreating, squaresWordCreating, wordPlace);
        lettersPlace.setLayoutX(0);
        lettersPlace.setLayoutY(constants.centerY+248);

        //Панель для слова
            wordPlace.setLayoutX(constants.centerX/2);
            wordPlace.setLayoutY(constants.centerY-200);



        //Задание
            definition.setPrefWidth(230);
            definition.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            definition.setText(wordDefinitionManager.getDefinition());
            definition.setWrapText(true);
            definitionContainer.setLayoutX(constants.centerX+170);
            definitionContainer.setLayoutY(constants.centerY-100);

        //Панель для барабана
            Pane drumPane = new Pane();
            drumPane.setPrefSize(constants.centerX * 2, constants.centerY * 2);

            Group drumGroup = new Group();
            drumGroup.getChildren().addAll(drumPane, drumElements.getArrowLine(), drumElements.getArrowHead());

        //Создание секторов
            new SectorsCreating(constants.numSectors, constants.centerX, constants.centerY, constants.radius, constants.anglePerSector, drumPane);
            drumPane.getChildren().addAll(drumElements.getCircleBorder(), drumElements.getCircleClick());

        //Создание текста на секторах
            new SectorTextCreator(constants.numSectors, constants.anglePerSector, constants.centerX, constants.centerY, constants.radius, drumPane, sectorTexts);

            wheelPane.getChildren().add(drumGroup);

        //Анимация
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
//        });
//        pause.play();
    }
}
