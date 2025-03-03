package org.example.pole_chudes.gamePageClasses;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Letters {
    private Word word;
    public Letters(GridPane lettersGrid, String word) {
        Const constants = new Const();

        int col = 0;
        int row = 0;
        for (int i = 0; i < constants.letters.length(); i++) {
            Button button = new Button(String.valueOf(constants.letters.charAt(i)));
            button.setStyle("-fx-font-size: 12.6px");
            lettersGrid.add(button, col, row);
            button.setOnAction(event -> {
                new ClickLetterAction(button.getText(), word);
                button.setDisable(true);
            });
            col++;
            if (col > 32) {
                col = 0;
                row++;
            }
        }
    }
}
