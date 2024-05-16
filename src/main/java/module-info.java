module com.example.parkingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parkingsystem to javafx.fxml;
    exports com.example.parkingsystem;
}