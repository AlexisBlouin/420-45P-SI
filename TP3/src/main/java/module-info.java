module com.example.tp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.tp3 to javafx.fxml;
    exports com.example.tp3;
}