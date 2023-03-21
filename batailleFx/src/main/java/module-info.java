module com.example.bataillefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bataillefx to javafx.fxml;
    exports com.example.bataillefx;
}