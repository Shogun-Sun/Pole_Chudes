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
    private Pane curtainPane;

    @FXML
    private Pane definitionContainer;

    @FXML
    private Pane arrowPane;

    @FXML
    private Label pla1;

    @FXML
    private Label pla2;

    @FXML
    private Label pla3;

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
            "100", "\uD83D\uDDDD", "Б", "500", "☎", "1000", "x2", "300",
            "400", "П", "0", "+", "600", "700", "Б", "550",
            "50", "☎", "+", "450", "250", "350", "150", "650"
    );

    public String letters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    @FXML
    public void initialize() {
//        PauseTransition pause = new PauseTransition(Duration.millis(1));
//        pause.setOnFinished(event -> {
            Const constants = new Const();
            DrumElements drumElements = new DrumElements(constants.centerX, constants.centerY, constants.radius);

            WordDefinitionManager wordDefinitionManager = new WordDefinitionManager();

        //Поле для загаданного слова
        LabelsWordLetterCreating labelsWordLetterCreating = new LabelsWordLetterCreating(wordDefinitionManager.getWord(), 30, 8, wordPlace);
        SquaresWordCreating squaresWordCreating = new SquaresWordCreating(wordDefinitionManager.getWord(), 30, 8, wordPlace);

        //Панель для выбора буквы
        CurtainLetterCreating curtainLetterCreating = new CurtainLetterCreating();
        LabelsLettersCreating labelsLettersCreating = new LabelsLettersCreating(letters, 20, 5.5, lettersPlace);
        SquaresLettersCreating squaresLettersCreating = new SquaresLettersCreating(letters, wordDefinitionManager.getWord(), 20, 5.5, lettersPlace, labelsLettersCreating, squaresWordCreating, curtainPane,
                () -> curtainLetterCreating.CreateCurtain(curtainPane));
        lettersPlace.setLayoutX(0);
        lettersPlace.setLayoutY(constants.centerY+248);

        lettersPlace.setStyle("-fx-max-height: 10px");


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
            wheelPane.setPrefSize(constants.centerX * 2, constants.centerY * 2);

            Group drumGroup = new Group();
            drumGroup.getChildren().addAll(drumElements.getCircleMiddle(), drumElements.getCircleBorder(), drumElements.getCircleClick());
            arrowPane.getChildren().addAll(drumElements.getArrowLine(), drumElements.getArrowHead());

        //Создание секторов
            new SectorsCreating(constants.numSectors, constants.centerX, constants.centerY, constants.radius, constants.anglePerSector, wheelPane);
//            drumPane.getChildren().addAll(drumElements.getCircleBorder(), drumElements.getCircleClick());

        //Создание текста на секторах
            new SectorTextCreator(constants.numSectors, constants.anglePerSector, constants.centerX, constants.centerY, constants.radius, wheelPane, sectorTexts);

            wheelPane.getChildren().add(drumGroup);


            curtainPane.setLayoutY(515);
        //Анимация
            AnimationManager animationManager = new AnimationManager(wheelPane, constants.anglePerSector, sectorTexts,
            drumElements, () -> {
                curtainLetterCreating.RemoveCurtain(curtainPane);
            });

        //Перегородка для букв

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
                        System.out.println("Увы, вы банкрот");
                        scorepla1 = 0;
                        score = 0;
                        pla1.setText("0");
                        value = "";
                        break;
                    case "☎":
                        System.out.println("Сектор шанс на барабане");
                        score = 0;
                        value = "";

                        break;
                    case "+":
                        System.out.println("Сектор + на барабане, откройте любую букву");
                        score = 0;
                        value = "";
                        break;

                    case "\uD83D\uDDDD":
                        System.out.println("Сектор ключ на барабане");
                        score = 0;
                        value = "";

                    case "x2":
                        scorepla1 = scorepla1 * 2;
                        pla1.setText(String.valueOf(scorepla1));
                        value = "";
                        score = 0;

                    case "П":
                        System.out.println("Сектор приз на барабане");
                        score = 0;
                        value = "";

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
