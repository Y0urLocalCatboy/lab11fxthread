module com.example.lab11fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.lab11fx to javafx.fxml;
    exports com.example.lab11fx;
}