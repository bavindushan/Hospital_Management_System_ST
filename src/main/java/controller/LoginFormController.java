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
                    System.out.println(e.getMessage());
                }
                break;
            case "Receptionist":
                System.out.println("Receptionist");
                try {
                    searchReceptionist();
                } catch (SQLException | IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "Staff":
                System.out.println("Staff");
                try {
                    searchStaff();
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
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
            }else new Alert(Alert.AlertType.ERROR,"Incorrect Password!");
        }else new Alert(Alert.AlertType.ERROR,"Receptionist not available!");
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
                new Alert(Alert.AlertType.CONFIRMATION,"Login successful!");
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/StaffDashBoard.fxml"))));
                stage.show();
            }else new Alert(Alert.AlertType.ERROR,"Incorrect Password!");
        }else new Alert(Alert.AlertType.ERROR,"Staff member not available!");
    }
}
