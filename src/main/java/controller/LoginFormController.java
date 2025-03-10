package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.Doctor;
import model.Patient;
import model.Staff;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                try {
                    searchAdmin();
                } catch (SQLException | IOException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
                break;
            case "Receptionist":
                System.out.println("Receptionist");
                try {
                    searchReceptionist();
                } catch (SQLException | IOException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
                break;
            case "Doctor":
                System.out.println("Doctor");
                try {
                    searchDoctor();
                } catch (SQLException | IOException e) {
                    System.out.println("An Error occurred: "+e.getMessage());
                }
                break;
            case "Patient":
                System.out.println("Patient");
                try {
                    searchPatient();
                } catch (SQLException | IOException e) {
                    System.out.println("An Error occurred: "+e.getMessage());
                }
                break;
            default:
                System.out.println("Unknown user type");
        }
    }
    private void searchAdmin() throws SQLException, IOException {
        if (cmbUserTypes.getValue() == null ||
                txtEmail.getText().trim().isEmpty() ||
                txtPassword.getText().trim().isEmpty()) {

            new Alert(Alert.AlertType.WARNING, "All Fields Should Be Filled!").show();
            return;
        }

        System.out.println("searchAdmin Method call");
        String SQL = "SELECT * FROM admin WHERE email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()){
            System.out.println("searchAdmin method if statement 1 work");
            Admin admin = new Admin(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            //password validation process

            //password encrypt
            String key = "#E&Cr!Pt$";
            BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
            basicTextEncryptor.setPassword(key);
            String decryptedPassword = basicTextEncryptor.decrypt(admin.getAdminPassword());

            if (decryptedPassword.equals(txtPassword.getText())){
                System.out.println("search method if statement 2 work");
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoard.fxml"))));
                stage.show();
            }else new Alert(Alert.AlertType.ERROR, "Incorrect Password!").show();
        }else new Alert(Alert.AlertType.ERROR, "User Not Found!").show();
    }
    private void searchReceptionist() throws SQLException, IOException {
        if (cmbUserTypes.getValue() == null ||
                txtEmail.getText().trim().isEmpty() ||
                txtPassword.getText().trim().isEmpty()) {

            new Alert(Alert.AlertType.WARNING, "All Fields Should Be Filled!").show();
            return;
        }

        System.out.println("searchAdmin Method call");
        String SQL = "SELECT * FROM staff WHERE role = 'Receptionist' AND email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()){
            System.out.println("searchReceptionist method if statement 1 work");

            Staff staff = new Staff(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            if (staff.getStaffPassword().equals(txtPassword.getText())){
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoard.fxml"))));
                stage.show();
            }else new Alert(Alert.AlertType.ERROR,"Incorrect Password!").show();
        }else new Alert(Alert.AlertType.ERROR,"Receptionist not available!").show();
    }
    private void searchDoctor() throws SQLException, IOException {
        if (cmbUserTypes.getValue() == null ||
                txtEmail.getText().trim().isEmpty() ||
                txtPassword.getText().trim().isEmpty()) {

            new Alert(Alert.AlertType.WARNING, "All Fields Should Be Filled!").show();
            return;
        }

        String SQL = " SELECT * FROM doctor WHERE email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if(resultSet.next()){
            Doctor doctor = new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
            if(doctor.getPassword().equals(txtPassword.getText())){
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DoctorDasBoard.fxml"))));
                stage.show();
            }else new Alert(Alert.AlertType.ERROR,"Incorrect Password!").show();
        }else new Alert(Alert.AlertType.ERROR,"Doctor not found!").show();
    }
    private void searchPatient() throws SQLException, IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PatientDashBoard.fxml"))));
        stage.show();
    }
}
