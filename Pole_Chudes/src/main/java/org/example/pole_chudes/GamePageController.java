package org.example.pole_chudes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.example.pole_chudes.gamePageClasses.*;
import org.example.pole_chudes.gamePageClasses.UsesDependencies;
//import org.example.pole_chudes.gamePageClasses.SquaresLettersCreating;

public class GamePageController {
    @FXML
    private Pane wheelPane;

    @FXML
    private GridPane wordPlace;

    @FXML
    private GridPane lettersPlace;

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

    private Runnable curtainTask;

    public static int currentPlayer = 1;
    public static boolean selectLetter = false;

    static int scorepla1 = 0;
    static int scorepla2 = 0;
    static int scorepla3 = 0;

    private boolean isBotTurn = false;

    @FXML
    public void initialize() {
            Const constants = new Const();
            DrumElements drumElements = new DrumElements(constants.centerX, constants.centerY, constants.radius);

            WordDefinitionManager wordDefinitionManager = new WordDefinitionManager();

        //Поле для загаданного слова
        new Word(wordPlace, wordDefinitionManager.getWord());

        //Панель для выбора буквы
        CurtainLetterCreating curtainLetterCreating = new CurtainLetterCreating();

        UsesDependencies usesDependencies = new UsesDependencies(wheelPane, drumElements, lettersPlace); //labelsLettersCreating

        curtainTask = () -> curtainLetterCreating.CreateCurtain(curtainPane);
        new Letters(lettersPlace, wordDefinitionManager.getWord());


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
            drumGroup.getChildren().addAll(drumElements.getCircleMiddle(), drumElements.getCircleBorder());
            arrowPane.getChildren().addAll(drumElements.getArrowLine(), drumElements.getArrowHead());

        //Создание секторов
            new SectorsCreating(constants.numSectors, constants.centerX, constants.centerY, constants.radius, constants.anglePerSector, wheelPane);

        //Создание текста на секторах
            new SectorTextCreator(constants.numSectors, constants.anglePerSector, constants.centerX, constants.centerY, constants.radius, wheelPane, constants.sectorTexts);

            wheelPane.getChildren().add(drumGroup);
            wheelPane.getChildren().add(drumElements.getCircleClick());

            curtainPane.setLayoutY(515);
        //Анимация
            AnimationManager animationManager = new AnimationManager(wheelPane, constants.anglePerSector, constants.sectorTexts,
            drumElements, () -> {
                curtainLetterCreating.RemoveCurtain(curtainPane);
            });

        animationManager.setOnAnimationEnd(() -> {
            processScore(animationManager);
            });

            usesDependencies.setAnimationManager(animationManager);
    }

    public Runnable getCurtainTask() {
        return curtainTask;
    }

    public void nextTurn(AnimationManager animationManager, DrumElements drumElements) {
        currentPlayer++;
        if (currentPlayer > 3) {
            currentPlayer = 1;
        }
        checkPlayerTurn(animationManager, drumElements);
    }

    private void checkPlayerTurn(AnimationManager animationManager, DrumElements drumElements) {
        if (currentPlayer == 2|| currentPlayer == 3 ) {
            if (selectLetter) {
                animationManager.autoStartAnimation(wheelPane, drumElements);
            }

        } else if (currentPlayer == 1) {
            animationManager.mouseWheelClick(drumElements, wheelPane);
            animationManager.keyboardWheelClick(drumElements, wheelPane);
        }
    }

    public void processScore(AnimationManager animationManager) {
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
                break;

            case "x2":
                scorepla1 = scorepla1 * 2;
                pla1.setText(String.valueOf(scorepla1));
                value = "";
                score = 0;
                break;

            case "П":
                System.out.println("Сектор приз на барабане");
                score = 0;
                value = "";
                break;

            default:
                scorepla1 += score;
                pla1.setText(String.valueOf(scorepla1));
                break;
        }
    }



}
