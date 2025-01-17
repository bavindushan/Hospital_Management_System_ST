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
        userList.add("Staff");
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
                    System.err.println("An error occurred: " + e.getMessage());
                    //e.printStackTrace(); //  For detailed debugging
                }
                break;
            case "Staff":
                System.out.println("Staff");
                try {
                    searchStaff();
                } catch (SQLException | IOException e) {
                    System.err.println("An error occurred: " + e.getMessage());
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
        System.out.println("searchAdmin Method call");
        String SQL = "SELECT * FROM admin WHERE email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()){
            System.out.println("searchAdmin method if statement 1 work");
            Admin admin = new Admin(resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4)
            );
            //password validation process
            if (admin.getAdminPassword().equals(txtPassword.getText())){
                System.out.println("search method if statement 2 work");
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoard.fxml"))));
                stage.show();
            }else new Alert(Alert.AlertType.ERROR, "Incorrect Password!").show();
        }else new Alert(Alert.AlertType.ERROR, "User Not Found!").show();
    }
    private void searchReceptionist() throws SQLException, IOException {
        System.out.println("searchAdmin Method call");
        String SQL = "SELECT * FROM staff WHERE role = 'Receptionist' AND email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()){
            System.out.println("searchReceptionist method if statement 1 work");

            Staff staff = new Staff(resultSet.getString(2),
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
    private void searchStaff() throws SQLException, IOException {
        String SQL = "SELECT * FROM staff WHERE email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()){
            Staff staff = new Staff(resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getString(6)
            );
            if (staff.getStaffPassword().equals(txtPassword.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Login successful!").show();
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/StaffDashBoard.fxml"))));
                stage.show();
            }else new Alert(Alert.AlertType.ERROR,"Incorrect Password!").show();
        }else new Alert(Alert.AlertType.ERROR,"Staff member not available!").show();
    }
    private void searchDoctor() throws SQLException, IOException {
        String SQL = " SELECT * FROM doctor WHERE email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if(resultSet.next()){
            Doctor doctor = new Doctor(resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getString(5),
                                        resultSet.getString(6),
                                        resultSet.getString(7),
                                        resultSet.getString(8)
            );
            if(doctor.getDoctorPassword().equals(txtPassword.getText())){
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DoctorDasBoard.fxml"))));
                stage.show();
            }else new Alert(Alert.AlertType.ERROR,"Incorrect Password!").show();
        }else new Alert(Alert.AlertType.ERROR,"Doctor not found!").show();
    }
    private void searchPatient() throws SQLException, IOException {
        String SQL = "SELECT * FROM patient WHERE email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if(resultSet.next()){
            Patient patient = new Patient(resultSet.getString(2),
                                            resultSet.getString(3),
                                            resultSet.getString(4),
                                            resultSet.getString(5),
                                            resultSet.getString(6),
                                            resultSet.getString(7),
                                            resultSet.getString(8)
            );
            if(patient.getPatientPassword().equals(txtPassword.getText())){
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PatientDashBoard.fxml"))));
                stage.show();
            }else new Alert(Alert.AlertType.ERROR,"Invalid Password!").show();
        }else new Alert(Alert.AlertType.ERROR,"Patient not Found!").show();
    }
}
