package org.example.pole_chudes.gamePageClasses.yakubovich;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.pole_chudes.GamePageController;
import org.example.pole_chudes.gamePageClasses.Const;

import java.io.InputStream;
import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class YakubovichAnimation {
    private static ImageView imageView;
    private static Image state1;
    private static Image state2;
    private Media media;
    private MediaPlayer mediaPlayer;

    public YakubovichAnimation() {
    }

    public void startAnimation(Pane yakubovich, String text) {
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

        updateImage(text);
    }

    public void updateImage(String text) {
        if (text == null || text.isEmpty()) return;

        char lastChar = text.charAt(text.length() - 1);

        if (Character.isLetterOrDigit(lastChar) || "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".contains(String.valueOf(lastChar))) {
            imageView.setImage(state1);
            playSound();
        } else if (lastChar == ' ') {
            imageView.setImage(state2);
            stopSound();
        }
    }

    private void playSound() {
        if (mediaPlayer != null && mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            mediaPlayer.play();
        }
    }

    private void stopSound() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
    }
}