package controller.Prescription;

import service.custom.impl.PatientBoImpl;
import service.custom.impl.DoctorBoImpl;
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
import service.custom.impl.PrescriptionBoImpl;

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

    PrescriptionBoImpl prescriptionBoImpl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prescriptionBoImpl = new PrescriptionBoImpl();
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
        String lastId = prescriptionBoImpl.getLastId();
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

        List<Prescription> all = prescriptionBoImpl.getAll();
        ObservableList<Prescription> observableList = FXCollections.observableArrayList();

        all.forEach(prescription -> observableList.add(prescription));
        tblprescriptions.setItems(observableList);
    }
    private void loadDoctorID(){
        DoctorBoImpl doctorBoImpl = new DoctorBoImpl();
        try {
            List<Doctor> doctorList = doctorBoImpl.getAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();

            doctorList.forEach(doctor -> observableList.add(doctor.getId()));
            cmbdoctorId.setItems(observableList);

        } catch (SQLException e) {
            System.out.println("An error occur"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur"+e.getMessage()).show();
        }
    }
    private void loadPatientsID(){
        PatientBoImpl patientBoImpl = new PatientBoImpl();
        try {
            List<Patient> patientList = patientBoImpl.getAll();
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
            boolean isAdd = prescriptionBoImpl.add(new Prescription(
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
            boolean isDelete = prescriptionBoImpl.delete(txtId.getText());
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
            Prescription prescription = prescriptionBoImpl.search(txtId.getText());
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
            boolean isUpdate = prescriptionBoImpl.update(new Prescription(
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
