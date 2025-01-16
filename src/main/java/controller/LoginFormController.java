package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private ComboBox cmbUserTypes;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCmb();
    }
    private void loadCmb(){
        ObservableList<String> userList = FXCollections.observableArrayList();
        userList.add("Admin");
        userList.add("Receptionist");
        userList.add("Doctor");
        userList.add("Patient");

        cmbUserTypes.setItems(userList);
    }
    @FXML
    void btnLoginOnAction(ActionEvent event) {
        switch (cmbUserTypes.getValue().toString()) {
            case "Admin":
                System.out.println("Admin");
                break;
            case "Receptionist":
                System.out.println("Receptionist");
                break;
            case "Doctor":
                System.out.println("Doctor");
                break;
            case "Patient":
                System.out.println("Patient");
                break;
            default:
                System.out.println("Unknown user type");
        }
    }
}
