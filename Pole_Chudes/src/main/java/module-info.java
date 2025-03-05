module org.example.pole_chudes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jsoup;
    requires org.json;
    requires jdk.compiler;
    requires javafx.media;
    requires transitive javafx.graphics;

    opens org.example.pole_chudes to javafx.fxml;
    exports org.example.pole_chudes;
    exports org.example.pole_chudes.gamePageClasses;
    opens org.example.pole_chudes.gamePageClasses to javafx.fxml;
    exports org.example.pole_chudes.gamePageClasses.drum;
    opens org.example.pole_chudes.gamePageClasses.drum to javafx.fxml;
    exports org.example.pole_chudes.gamePageClasses.drum.Sectors;
    opens org.example.pole_chudes.gamePageClasses.drum.Sectors to javafx.fxml;
    exports org.example.pole_chudes.gamePageClasses.wordLetters;
    opens org.example.pole_chudes.gamePageClasses.wordLetters to javafx.fxml;
    exports org.example.pole_chudes.gamePageClasses.yakubovich;
}