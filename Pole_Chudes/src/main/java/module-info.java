module org.example.pole_chudes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jsoup;
    requires org.json;
    requires jdk.compiler;

    opens org.example.pole_chudes to javafx.fxml;
    exports org.example.pole_chudes;
    exports org.example.pole_chudes.gamePageClasses;
//    exports org.example.pole_chudes.customcomponents to javafx.fxml;
    opens org.example.pole_chudes.gamePageClasses to javafx.fxml;
}