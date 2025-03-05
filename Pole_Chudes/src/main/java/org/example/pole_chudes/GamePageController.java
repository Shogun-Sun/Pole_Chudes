package org.example.pole_chudes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.pole_chudes.gamePageClasses.*;
import org.example.pole_chudes.gamePageClasses.drum.AnimationManager;
import org.example.pole_chudes.gamePageClasses.drum.DrumElements;
import org.example.pole_chudes.gamePageClasses.drum.Sectors.SectorTextCreator;
import org.example.pole_chudes.gamePageClasses.drum.Sectors.SectorsCreating;
import org.example.pole_chudes.gamePageClasses.UsesDependencies;
import org.example.pole_chudes.gamePageClasses.yakubovich.YakubovichAnimation;
import org.example.pole_chudes.gamePageClasses.wordLetters.Letters;
import org.example.pole_chudes.gamePageClasses.wordLetters.Word;
import org.example.pole_chudes.gamePageClasses.wordLetters.WordDefinitionManager;

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
    private Pane dialogContainer;

    @FXML
    private Pane arrowPane;

    @FXML
    private Label pla1;

    @FXML
    private Label pla2;

    @FXML
    private Label pla3;

    @FXML
    private ScrollPane dialog;

    @FXML
    private Pane yakubovich;

    private int score = 0;
    private String value;

    static int scorepla1 = 0;
    static int scorepla2 = 0;
    static int scorepla3 = 0;

    private static Word word;
    private static Letters letters;
    private static Label dialogLabel = new Label();
    private static  YakubovichAnimation yakubovichAnimation = new YakubovichAnimation();
    private Duration duration;


    @FXML
    public void initialize() {
            Const constants = new Const();
            duration = Duration.seconds(10);
            yakubovichAnimation.startAnimation(yakubovich, duration);

            DrumElements drumElements = new DrumElements(constants.centerX, constants.centerY, constants.radius);

            WordDefinitionManager wordDefinitionManager = new WordDefinitionManager();

            UsesDependencies usesDependencies = new UsesDependencies(wheelPane, drumElements, lettersPlace); //labelsLettersCreating

        //Поле для букв
        letters = new Letters(lettersPlace, wordDefinitionManager.getWord());
        lettersPlace.setLayoutX(0);
        lettersPlace.setLayoutY(constants.centerY+248);
        lettersPlace.setStyle("-fx-max-height: 10px");

        //Поле для загаданного слова
            word = new Word(wordPlace, wordDefinitionManager.getWord());
            wordPlace.setLayoutX(constants.centerX/2);
            wordPlace.setLayoutY(constants.centerY-200);

        //Диалог
        dialog.setContent(setDialogText(wordDefinitionManager.getDefinition()));

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
            drumElements);

        animationManager.setOnAnimationEnd(() -> {
            processScore(animationManager);
            });

            usesDependencies.setAnimationManager(animationManager);
    }

    public void processScore(AnimationManager animationManager) {
        try{
            String text = "Очков на барабане, ваша буква?";
            duration = Duration.seconds(4);
            yakubovichAnimation.startAnimation(yakubovich, duration);
            score = Integer.parseInt(animationManager.getSelectedValue());
            letters.enableButtons();
            dialog.setContent(setDialogText(String.valueOf(score) + " очков на барабане, ваша буква?"));
        } catch (NumberFormatException e){
            value = animationManager.getSelectedValue();
        }

        if(value == null){
            value = "";
        }

        switch (value){
            case "Б":
                dialog.setContent(setDialogText("Сектор " + String.valueOf(value) + " на барабане, увы, вы банкрот"));
                word.disableWordButtons();
                value="";
                break;

            case "+":
                System.out.println("Сектор + на барабане, откройте любую букву");
                letters.disableButtons();
                value="";
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

    public Label setDialogText(String definition) {
        dialogLabel.setText(definition);
        dialogLabel.setWrapText(true);
        dialogLabel.setMaxWidth(135);
        return dialogLabel;
    }
}
