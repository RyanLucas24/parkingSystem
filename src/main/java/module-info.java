module com.example.parkingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.parkingsystem to javafx.fxml;
    exports com.example.parkingsystem;
    exports com.example.parkingsystem.application.main;
    opens com.example.parkingsystem.application.main to javafx.fxml;
    exports com.example.parkingsystem.domain.model.payment;
    opens com.example.parkingsystem.domain.model.payment to javafx.fxml;
}