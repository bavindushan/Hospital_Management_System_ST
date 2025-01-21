package controller.doctor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Doctor;
import org.jasypt.util.text.BasicTextEncryptor;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorFormController implements Initializable {

    @FXML
    public TableColumn clmQualification;

    @FXML
    public TableColumn clmPassword;

    @FXML
    public TableColumn clmAvailability;

    @FXML
    public TableColumn clmEmail;

    @FXML
    public TableColumn clmSpeciality;

    @FXML
    public TableColumn clmTel;

    @FXML
    private CheckBox cbAvilability;


    @FXML
    private TableColumn clmID;

    @FXML
    private TableColumn clmName;


    @FXML
    private ComboBox cmbQualification;

    @FXML
    private ComboBox cmbSpeciality;

    @FXML
    private TableView tblAdminTable;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtTelNo;

    DoctorController doctorController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSpeciality();
        loadQualifications();
        doctorController = new DoctorController();
        loadTable();
        txtId.setText(genarateDoctorID());
    }
    private void loadSpeciality(){
        ObservableList<String> specialityList = FXCollections.observableArrayList();
        specialityList.add("Cardiology");
        specialityList.add("Neurology");
        specialityList.add("Pediatrics");
        specialityList.add("Orthopedics");
        specialityList.add("Dermatology");
        cmbSpeciality.setItems(specialityList);
    }
    private void loadQualifications(){
        ObservableList<String> qualificationList = FXCollections.observableArrayList();
        qualificationList.add("MBBS, MD ");
        qualificationList.add("MBBS, DM");
        qualificationList.add("MBBS, DCH");
        qualificationList.add("MBBS, MS");
        qualificationList.add("MBBS, DD");
        cmbQualification.setItems(qualificationList);
    }
    private void loadTable(){
        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmSpeciality.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        clmTel.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        clmQualification.setCellValueFactory(new PropertyValueFactory<>("qualifications"));
        clmAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));


        ObservableList<Doctor> observableList = FXCollections.observableArrayList();
        try {
            List<Doctor> doctorList = doctorController.getAll();
            doctorList.forEach(doctor -> {
                observableList.add(doctor);
            });
            tblAdminTable.setItems(observableList);
        } catch (SQLException e) {
            System.out.println("An error occur! "+ e.getMessage());
        }
    }

    private String genarateDoctorID(){
        try {
            String lastID = doctorController.getLastID();

            if (lastID == null) return "D001";

            int numericPart = Integer.parseInt(lastID.substring(1));
            int newNumber = numericPart + 1;

            return String.format("D%03d", newNumber);

        } catch (SQLException e) {
            System.err.println("Error generating Doctor ID: " + e.getMessage());
            return "D001"; // Return default ID in case of an error
        } catch (NumberFormatException e) {
            System.err.println("Error parsing Doctor ID: " + e.getMessage());
            return "D001"; // Return default ID if parsing fails
        }
    }

    private void resetTextxBox(){
        txtId.setText(genarateDoctorID());
        txtEmail.setText("");
        txtName.setText("");
        txtTelNo.setText("");
        txtPassword.setText("");
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        // Validate input fields
        if (txtId.getText().isEmpty() ||
                txtName.getText().isEmpty() ||
                cmbSpeciality.getValue() == null ||
                txtTelNo.getText().isEmpty() ||
                cmbQualification.getValue() == null ||
                txtEmail.getText().isEmpty()) {

            new Alert(Alert.AlertType.WARNING, "Please fill in all fields before submitting.").show();
            return;
        }

        try {
            //password encryption
            String key = "@#$%^&*()+";
            BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
            basicTextEncryptor.setPassword(key);
            String encryptedPassword = basicTextEncryptor.encrypt(txtPassword.getText());

            boolean isAdded = doctorController.addDoctor(new Doctor(
                    txtId.getText(),
                    txtName.getText(),
                    cmbSpeciality.getValue().toString(),
                    txtTelNo.getText(),
                    cmbQualification.getValue().toString(),
                    cbAvilability.isSelected() ? "Available" : "Not-available",
                    txtEmail.getText(),
                    encryptedPassword
            ));

            if ((isAdded)) {
                new Alert(Alert.AlertType.INFORMATION, "Doctor Added Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Unsuccessfull!").show();
            }
        } catch (SQLException e) {
            System.out.println("an error occurd! "+e.getMessage());
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
