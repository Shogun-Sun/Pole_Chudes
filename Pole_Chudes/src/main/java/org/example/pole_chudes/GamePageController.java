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

import java.util.ArrayDeque;
import java.util.Queue;

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

    private boolean isBotStep = false;
    private static boolean wheelState = true;
    private static boolean wordLetterState = false;
    private static boolean WAITING_FOR_SPIN = false;
    private static boolean WAITING_FOR_LETTER = false;
    private boolean onBotWaitingForDialogEndChoseLetter = false;
    private boolean onBotWaitingForDialogEndDrumAnimation = false;

    private Queue<String> dialogQueue = new ArrayDeque<>();
    private boolean isDialogPlaying = false;

    public enum GameState{
        PLAYER,
        BOT1,
        BOT2
    }
    public static GameState currentState = GameState.PLAYER;

    private static WordDefinitionManager wordDefinitionManager;
    private static AnimationManager animationManager;
    private static DrumElements drumElements;
    private static Word word;
    private static Letters letters;
    private static Label dialogLabel = new Label();
    private static final YakubovichAnimation yakubovichAnimation = new YakubovichAnimation();
    private static Timeline timeline;

    @FXML
    public void initialize() {
        pla1.setText(String.valueOf(scorepla1));
        pla2.setText(String.valueOf(scorepla2));
        pla3.setText(String.valueOf(scorepla3));

        Const constants = new Const();
        drumElements = new DrumElements(constants.centerX, constants.centerY, constants.radius);
        wordDefinitionManager = new WordDefinitionManager();
        System.out.println(wordDefinitionManager.getWord());

        yakubovichAnimationStart(wordDefinitionManager.getDefinition());
        UsesDependencies usesDependencies = new UsesDependencies(wheelPane, drumElements, lettersPlace);

        //Поле для букв
        letters = new Letters(lettersPlace, wordDefinitionManager.getWord(), this);
        lettersPlace.setLayoutX(0);
        lettersPlace.setLayoutY(constants.centerY+248);
        lettersPlace.setStyle("-fx-max-height: 10px");

        //Поле для загаданного слова
        word = new Word(wordPlace, wordDefinitionManager.getWord(), this);
        wordPlace.setLayoutX(constants.centerX/2);
        wordPlace.setLayoutY(constants.centerY-200);

        //Диалог
        setDialogText(wordDefinitionManager.getDefinition());

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

        curtainPane.setLayoutY(515);

        //Анимация
        animationManager = new AnimationManager(wheelPane, constants.anglePerSector, constants.sectorTexts,
                drumElements);

        animationManager.setOnAnimationEnd(() -> {
            processScore(animationManager);
            setWaitingForSpin(true);
        });

        usesDependencies.setAnimationManager(animationManager);
    }

    public void processScore(AnimationManager animationManager) {
        try {
            String text = "Очков на барабане, буква?";
            yakubovichAnimationStart(text);
            wheelState = false;
            score = Integer.parseInt(animationManager.getSelectedValue());
            setDialogText(String.valueOf(score) + " очков на барабане, ваша буква?");
        } catch (NumberFormatException e) {
            value = animationManager.getSelectedValue();
        }

        if (value == null) {
            value = "";
        }

        switch (value) {
            case "Б":
                setDialogText("Сектор " + String.valueOf(value) + " на барабане, увы, вы банкрот");
                word.disableWordButtons();
                value = "";
                break;

            case "+":
                setDialogText("Сектор + на барабане, откройте любую букву в слове");
                wordLetterState = true;
                value = "";
                break;

            case "\uD83D\uDDDD":
                System.out.println("Сектор ключ на барабане");
                score = 0;
                value = "";
                break;

            case "x2":
                multiplyScore();
                value = "";
                score = 0;
                break;

            case "П":
                System.out.println("Сектор приз на барабане");
                score = 0;
                value = "";
                break;

            default:
                addScore();
                break;
        }

        if(isBotStep) {
            onBotWaitingForDialogEndChoseLetter = true;
        }

    }

    private void addScore() {
        switch (currentState) {
            case GameState.PLAYER:
                scorepla1 += score;
                pla1.setText(String.valueOf(scorepla1));
                break;
            case GameState.BOT1:
                scorepla2 += score;
                pla2.setText(String.valueOf(scorepla2));
                break;
            case GameState.BOT2:
                scorepla3 += score;
                pla3.setText(String.valueOf(scorepla3));
                break;
        }
    }

    private void multiplyScore() {
        switch (currentState) {
            case GameState.PLAYER:
                scorepla1 *= 2;
                pla1.setText(String.valueOf(scorepla1));
                break;
            case GameState.BOT1:
                scorepla2 *= 2;
                pla2.setText(String.valueOf(scorepla2));
                break;
            case GameState.BOT2:
                scorepla3 *= 2;
                pla3.setText(String.valueOf(scorepla3));
                break;
        }
    }

    public void setDialogText(String definition) {
        dialogQueue.add(definition);
        if (!isDialogPlaying) {
            playNextDialog();
        }
    }

    private void playNextDialog() {
        if (dialogQueue.isEmpty()) {
            isDialogPlaying = false;

            if (onBotWaitingForDialogEndChoseLetter) {
                onBotWaitingForDialogEndChoseLetter = false;

                Timeline delay = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                    letters.pressRandomButton(wordDefinitionManager.getWord(), this);
                    isBotStep = false;
                    moveTurn();
                }));
                delay.play();
            }

            if(onBotWaitingForDialogEndDrumAnimation) {
                onBotWaitingForDialogEndDrumAnimation = false;

                Timeline delay = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
                    animationManager.autoStartAnimation(wheelPane, drumElements);
                }));
                delay.play();
            }

            return;
        }

        isDialogPlaying = true;
        String definition = dialogQueue.poll();
        dialogLabel.setText("");
        dialogLabel.setWrapText(true);
        dialogLabel.setMaxWidth(135);

        final int[] charIndex = {0};
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (charIndex[0] < definition.length()) {
                dialogLabel.setText(dialogLabel.getText() + definition.charAt(charIndex[0]));
                scrollToBottom();
                yakubovichAnimation.updateImage(dialogLabel.getText());
                charIndex[0]++;
                circleClickDisable();
            } else {
                timeline.stop();

                moveTurn();

                isDialogPlaying = false;
                playNextDialog();
            }
        }));

        timeline.setCycleCount(definition.length() + 1);
        timeline.play();
        dialog.setContent(dialogLabel);
    }

    public void circleClickDisable(){
        wheelPane.getChildren().remove(drumElements.getCircleClick());

    }

    public void circleClickEnable(){
        if (!wheelPane.getChildren().contains(drumElements.getCircleClick())) {
            wheelPane.getChildren().add(drumElements.getCircleClick());
        }
    }

    public void yakubovichAnimationStart(String text) {
        yakubovichAnimation.startAnimation(yakubovich, text);
    }

    public static void setWheelState(boolean state) {
        wheelState = state;
    }

    public void scrollToBottom() {
        dialog.setVvalue(1.0);
    }

    public static void setWordLetterState(boolean state) {
        wordLetterState = state;
    }

    public static void setWaitingForSpin(boolean state){
        WAITING_FOR_SPIN = state;
    }

    public static void setWaitingForLetter(boolean waitingForLetter) {
        WAITING_FOR_LETTER = waitingForLetter;
    }

    public void switchTurn(){
        switch (currentState){
            case GameState.PLAYER:
                currentState = GameState.BOT1;
                break;

            case GameState.BOT1:
                currentState = GameState.BOT2;
                break;

            case GameState.BOT2:
                currentState = GameState.PLAYER;
                break;
            }
    }

    private void moveTurn(){
        if(isBotStep){
            return;
        }

        switch (currentState){
            case GameState.PLAYER:
                playerTurn();
                break;

            case GameState.BOT1, GameState.BOT2:
                botTurn();
                break;
        }
        if (wordLetterState) {
            word.enableWordButtons();
            wordLetterState = false;
        }
    }

    private void botTurn() {
        if (!isBotStep) {
            isBotStep = true;
            if(currentState != GameState.PLAYER) {
                    onBotWaitingForDialogEndDrumAnimation = true;
            }
        }
    }

    private void playerTurn() {
        if(wheelState){
            circleClickEnable();
        } else{
            circleClickDisable();
        }

        if(WAITING_FOR_SPIN){
            letters.enableButtons();
        } else{
            letters.disableButtons();
        }
    }

}
