module org.example.parcial2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.parcial2 to javafx.fxml, javafx.graphics;
    opens org.example.parcial2.viewController to javafx.fxml, javafx.graphics;

    exports org.example.parcial2;
}