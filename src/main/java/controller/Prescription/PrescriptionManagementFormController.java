package controller.Prescription;

import controller.Patient.PatientController;
import controller.doctor.DoctorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Doctor;
import model.Patient;
import model.Prescription;

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
        loadTable();
        txtId.setText(genarateID());
    }
    private void reloadForm(){
        loadTable();
        cmbpatientId.setValue("");
        cmbdoctorId.setValue("");
        txtDosage.setText("");
        txtDuration.setText("");
        txtMedicalDetails.setText("");
        txtAdditionalNotes.setText("");
        txtId.setText(genarateID());
    }
    private String genarateID(){
        String lastId = prescriptionController.getLastId();
        if (lastId==null) return "PRE001";

        int numericPart = Integer.parseInt(lastId.substring(3));
        int newNumber = numericPart+1;

        return String.format("PRE%03d",newNumber);
    }
    private void loadTable(){
        clmid.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmpatientId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        clmdoctorId.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        clmdosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        clmduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clmmedicaldetails.setCellValueFactory(new PropertyValueFactory<>("medicine_details"));
        clmadditionalnote.setCellValueFactory(new PropertyValueFactory<>("additional_notes"));

        List<Prescription> all = prescriptionController.getAll();
        ObservableList<Prescription> observableList = FXCollections.observableArrayList();

        all.forEach(prescription -> observableList.add(prescription));
        tblprescriptions.setItems(observableList);
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
        try {
            if (txtDuration.getText()==null&&txtDosage.getText()==null&&txtMedicalDetails.getText()==null&&
                    cmbpatientId.getValue().toString()==null&&cmbdoctorId.getValue().toString()==null) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields! ").show();
                return;
            }
            boolean isAdd = prescriptionController.add(new Prescription(
                    genarateID(),
                    cmbpatientId.getValue().toString(),
                    cmbdoctorId.getValue().toString(),
                    txtDosage.getText(),
                    txtDuration.getText(),
                    txtMedicalDetails.getText(),
                    txtAdditionalNotes.getText()
            ));
            if (isAdd) new Alert(Alert.AlertType.INFORMATION, "Added Successful!").show();
            else new Alert(Alert.AlertType.ERROR, "Unsuccessful!").show();
            reloadForm();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur!"+e.getMessage()).show();
            reloadForm();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDelete = prescriptionController.delete(txtId.getText());
            if (isDelete) new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
            else new Alert(Alert.AlertType.ERROR, "Unsuccessful!").show();
            reloadForm();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur!"+e.getMessage()).show();
            reloadForm();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        reloadForm();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            Prescription prescription = prescriptionController.search(txtId.getText());
            tblprescriptions.setItems(FXCollections.observableArrayList(prescription));

            cmbpatientId.setValue(prescription.getPatient_id());
            cmbdoctorId.setValue(prescription.getDoctor_id());
            txtDosage.setText(prescription.getDosage());
            txtDuration.setText(prescription.getDuration());
            txtMedicalDetails.setText(prescription.getMedicine_details());
            txtAdditionalNotes.setText(prescription.getAdditional_notes());

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur!"+e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (txtDuration.getText()==null&&txtDosage.getText()==null&&txtMedicalDetails.getText()==null&&
                    cmbpatientId.getValue().toString()==null&&cmbdoctorId.getValue().toString()==null) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields! ").show();
                return;
            }
            boolean isUpdate = prescriptionController.update(new Prescription(
                    txtId.getText(),
                    cmbpatientId.getValue().toString(),
                    cmbdoctorId.getValue().toString(),
                    txtDosage.getText(),
                    txtDuration.getText(),
                    txtMedicalDetails.getText(),
                    txtAdditionalNotes.getText()
            ));
            if (isUpdate) new Alert(Alert.AlertType.INFORMATION, "Update Successful!").show();
            else new Alert(Alert.AlertType.ERROR, "Unsuccessful!").show();
            reloadForm();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur!"+e.getMessage()).show();
            reloadForm();
        }
    }

}
