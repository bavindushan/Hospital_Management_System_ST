package controller.Prescription;

import controller.Patient.PatientController;
import controller.doctor.DoctorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Doctor;
import model.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PrescriptionManagementFormController implements Initializable {

    @FXML
    private TableColumn clmadditionalnote;

    @FXML
    private TableColumn clmdoctorId;

    @FXML
    private TableColumn clmdosage;

    @FXML
    private TableColumn clmduration;

    @FXML
    private TableColumn clmid;

    @FXML
    private TableColumn clmmedicaldetails;

    @FXML
    private TableColumn clmpatientId;

    @FXML
    private ComboBox cmbdoctorId;

    @FXML
    private ComboBox cmbpatientId;

    @FXML
    private TableView tblprescriptions;

    @FXML
    private TextField txtAdditionalNotes;

    @FXML
    private TextField txtDosage;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMedicalDetails;

    PrescriptionController prescriptionController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prescriptionController = new PrescriptionController();
        loadPatientsID();
        loadDoctorID();
    }
    private void loadDoctorID(){
        DoctorController doctorController = new DoctorController();
        try {
            List<Doctor> doctorList = doctorController.getAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();

            doctorList.forEach(doctor -> observableList.add(doctor.getId()));
            cmbdoctorId.setItems(observableList);

        } catch (SQLException e) {
            System.out.println("An error occur"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur"+e.getMessage()).show();
        }
    }
    private void loadPatientsID(){
        PatientController patientController = new PatientController();
        try {
            List<Patient> patientList = patientController.getAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();

            patientList.forEach(patient -> observableList.add(patient.getID()));
            cmbpatientId.setItems(observableList);
        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur!"+e.getMessage()).show();
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
