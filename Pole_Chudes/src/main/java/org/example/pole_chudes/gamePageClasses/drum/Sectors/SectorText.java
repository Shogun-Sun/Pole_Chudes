package org.example.pole_chudes.gamePageClasses.drum.Sectors;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SectorText {
    private Text text;
    public SectorText(
        double textX,
        double textY,
        String content,
        double angle
    ){
        text = new Text();
        text.setText(content);
        text.setX(textX);
        text.setY(textY);

        text.setTranslateX(-text.getBoundsInLocal().getWidth() / 2);
        text.setTranslateY(text.getBoundsInLocal().getHeight() / 4);
        text.setRotate(Math.toDegrees(angle) + 0);

        text.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        text.setFill(Color.BLACK);
    }

    public Text getText(){
        return text;
    }
}
