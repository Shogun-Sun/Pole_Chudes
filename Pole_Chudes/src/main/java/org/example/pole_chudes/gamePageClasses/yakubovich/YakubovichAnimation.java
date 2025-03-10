package org.example.pole_chudes.gamePageClasses.yakubovich;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.pole_chudes.GamePageController;
import org.example.pole_chudes.gamePageClasses.Const;

import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class YakubovichAnimation {
    private static ImageView imageView;
    private static Image state1;
    private static Image state2;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Random random = new Random();
    private GamePageController gamePageController;

    public YakubovichAnimation(GamePageController gamePageController) {
        this.gamePageController = gamePageController;
    }

    public void startAnimation(Pane yakubovich, Duration duration) {

        Const constants = new Const();
        InputStream inputStream1 = getClass().getResourceAsStream("/org/example/images/stateOne.png");
        InputStream inputStream2 = getClass().getResourceAsStream("/org/example/images/stateTwo.png");
        URL audioUrl = getClass().getResource("/org/example/audio/yakubovichSay.mp3");

        if (audioUrl != null) {
            media = new Media(audioUrl.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.3);
        } else {
            System.err.println("Аудиофайл не найден!");
            return;
        }

        assert inputStream1 != null;
        state1 = new Image(inputStream1);
        assert inputStream2 != null;
        state2 = new Image(inputStream2);

        imageView = new ImageView(state1);
        yakubovich.getChildren().add(imageView);
        yakubovich.setPrefHeight(300);
        yakubovich.setLayoutX(constants.centerX + 220);
        yakubovich.setLayoutY(constants.centerY + 80);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(random.nextDouble(400) + 300), event -> {
                    if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                        mediaPlayer.stop();
                    }
                    if (mediaPlayer != null) {
                        mediaPlayer.play();
                    }
                    if (imageView.getImage() == state1) {
                        imageView.setImage(state2);
                    } else {
                        imageView.setImage(state1);
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        KeyFrame stopFrame = new KeyFrame(duration, event -> {
            timeline.stop();
            gamePageController.circleClickEnable();
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            if (imageView.getImage() == state2) {
                imageView.setImage(state1);
            }
        });
        Timeline stopTimeline = new Timeline(stopFrame);
        stopTimeline.play();
    }
}