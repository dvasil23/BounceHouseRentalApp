module com.example.bouncerentalapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.dlsc.formsfx;

    opens com.example.bouncerentalapp to javafx.fxml;
    exports com.example.bouncerentalapp;
    exports com.example.bouncerentalapp.model;
    opens com.example.bouncerentalapp.model to javafx.fxml;
}