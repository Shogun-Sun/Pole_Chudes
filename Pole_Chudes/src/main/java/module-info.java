module org.example.pole_chudes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.pole_chudes to javafx.fxml;
    exports org.example.pole_chudes;
}