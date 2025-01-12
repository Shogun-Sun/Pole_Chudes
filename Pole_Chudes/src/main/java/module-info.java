module org.example.pole_chudes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jsoup;
    requires org.json;

    opens org.example.pole_chudes to javafx.fxml;
    exports org.example.pole_chudes;
    exports org.example.pole_chudes.gamePageClasses;
    opens org.example.pole_chudes.gamePageClasses to javafx.fxml;
}