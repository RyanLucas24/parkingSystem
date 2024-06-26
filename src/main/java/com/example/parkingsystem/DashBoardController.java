
package com.example.parkingsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author WillianCristian
 */
public class DashBoardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button addMensalist_add;

    @FXML
    private TextField addMensalist_adress;

    @FXML
    private TextField addMensalist_cpf;

    @FXML
    private Button addMensalist_edit;

    @FXML
    private TextField addMensalist_email;

    @FXML
    private TextField addMensalist_entryDate;

    @FXML
    private AnchorPane addMensalist_form;

    @FXML
    private TextField addMensalist_name;

    @FXML
    private TextField addMensalist_phone;

    @FXML
    private TextField addMensalist_plate;

    @FXML
    private Button addMensalist_remove;

    @FXML
    private Button close;

    @FXML
    private Button homeBtn;

    @FXML
    private Button managerBtn;

    @FXML
    private Button mensalistBtn;

    @FXML
    private TableColumn<?, ?> mensalist_adress;

    @FXML
    private TableColumn<?, ?> mensalist_cpf;

    @FXML
    private TableColumn<?, ?> mensalist_email;

    @FXML
    private TableColumn<?, ?> mensalist_entryDate;

    @FXML
    private TableColumn<?, ?> mensalist_name;

    @FXML
    private TableColumn<?, ?> mensalist_payment;

    @FXML
    private TableColumn<?, ?> mensalist_phone;

    @FXML
    private TableColumn<?, ?> mensalist_plate;

    @FXML
    private TextField mensalist_search;

    @FXML
    private Button minimize;

    @FXML
    private Button standard_cadBtn;

    @FXML
    private AnchorPane standard_form;

    @FXML
    private TextField standard_nomeBtn;

    @FXML
    private TextField standard_plateBtn;


    public void switchForm(ActionEvent e){

        if(e.getSource() == homeBtn){
            standard_form.setVisible(true);
            addMensalist_form.setVisible(false);

            homeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            mensalistBtn.setStyle("-fx-background-color:transparent");

        }else if(e.getSource() == mensalistBtn){
            addMensalist_form.setVisible(true);
            standard_form.setVisible(false);

            mensalistBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            homeBtn.setStyle("-fx-background-color:transparent");

        }
    }

    private double x=0;
    private double y=0;

    public void manager(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Gostaria de se Logar?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                //managerBtn.getScene().getWindow().hide(); não necessario
                Parent root = FXMLLoader.load(getClass().getResource("login_manager.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
