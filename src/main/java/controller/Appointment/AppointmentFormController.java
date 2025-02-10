package controller.Appointment;

import controller.Patient.PatientController;
import controller.doctor.DoctorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Doctor;
import model.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentFormController implements Initializable {

    @FXML
    private TableColumn clmDate;

    @FXML
    private TableColumn clmDoctorID;

    @FXML
    private TableColumn clmID;

    @FXML
    private TableColumn clmPatientID;

    @FXML
    private TableColumn clmStatus;

    @FXML
    private TableColumn clmTime;

    @FXML
    private ComboBox cmbDoctorID;

    @FXML
    private ComboBox cmbPatientID;

    @FXML
    private ComboBox cmbStatus;

    @FXML
    private ComboBox cmbTime;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TableView tblAppointmentTable;

    @FXML
    private TextField txtId;
    private AppointmentController appointmentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentController = new AppointmentController();
        loadTime();
        loadPatientsID();
        loadDoctorsID();
        loadStatus();
        txtId.setText(genarateID());
        loadTable();
    }
    private String genarateID(){
        String lastID = appointmentController.getLastID();
        if (lastID==null) return "AP000";

        int numericPart = Integer.parseInt(lastID.substring(2));
        int newNumber = numericPart+1;
        return String.format("AP%03d",newNumber);
    }
    private void loadTable(){

        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        clmDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        List<Appointment> all = appointmentController.getAll();
        ObservableList<Appointment> observableList = FXCollections.observableArrayList();

        all.forEach(appointment -> observableList.add(appointment));
        tblAppointmentTable.setItems(observableList);
    }
    private void loadTime(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("10:00:00 ");
        observableList.add("11:00:00 ");
        observableList.add("12:00:00 ");
        observableList.add("13:00:00 ");
        observableList.add("14:00:00 ");
        observableList.add("15:00:00 ");
        observableList.add("16:00:00 ");
        observableList.add("17:00:00 ");
        observableList.add("18:00:00 ");
        cmbTime.setItems(observableList);
    }
    private void loadPatientsID(){
        PatientController patientController = new PatientController();
        try {
            List<Patient> patientList = patientController.getAll();
            ObservableList<String> obsPatientsID = FXCollections.observableArrayList();

            patientList.forEach(patient -> obsPatientsID.add(patient.getID()));
            cmbPatientID.setItems(obsPatientsID);

        } catch (SQLException e) {
            System.out.println("An error occurd!"+e.getMessage());
        }
    }
    private void loadDoctorsID(){
        DoctorController doctorController = new DoctorController();
        try {
            List<Doctor> doctorList = doctorController.getAll();
            ObservableList<String> obsDoctorID = FXCollections.observableArrayList();

            doctorList.forEach(doctor -> obsDoctorID.add(doctor.getId()));
            cmbDoctorID.setItems(obsDoctorID);

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
    }
    private void loadStatus(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Scheduled");
        observableList.add("Cancelled");
        observableList.add("Completed");
        cmbStatus.setItems(observableList);
    }
    private void reloadForm(){
        txtId.setText(genarateID());
        loadTable();
        loadTime();
        loadStatus();
        loadPatientsID();
        loadDoctorsID();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (cmbPatientID.getValue() == null ||
                cmbDoctorID.getValue() == null ||
                dtpDate.getValue() == null ||
                cmbTime.getValue() == null ||
                cmbStatus.getValue() == null) {

            new Alert(Alert.AlertType.WARNING, "Please fill in all fields before adding an appointment.").show();
            return;
        }
        try {
            boolean isAdded = appointmentController.addAppointment(new Appointment(
                    genarateID(),
                    cmbPatientID.getValue().toString(),
                    cmbDoctorID.getValue().toString(),
                    dtpDate.getValue(),
                    cmbTime.getValue().toString(),
                    cmbStatus.getValue().toString()
            ));

            if (isAdded) new Alert(Alert.AlertType.INFORMATION, "Added successful!").show();
            else new Alert(Alert.AlertType.ERROR, "Unsuccessfully!").show();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }

        reloadForm();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (txtId.getText() == null ||
                cmbDoctorID.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields before deleting an appointment.").show();
            return;
        }
        try {
            boolean isDelete = appointmentController.deleteAppointment(txtId.getText(), cmbDoctorID.getValue().toString());
            if (isDelete) new Alert(Alert.AlertType.INFORMATION, "Delete Successful!").show();
            else new Alert(Alert.AlertType.ERROR, "Ünsuccessful!").show();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur! "+ e.getMessage()).show();
        }

        reloadForm();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

        reloadForm();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            Appointment appointment = appointmentController.searchAppointment(txtId.getText());
            if (appointment!=null){
                new Alert(Alert.AlertType.INFORMATION, "Appointment Found!").show();

                //set data to text box
                txtId.setText(appointment.getId());
                cmbPatientID.setValue(appointment.getPatientID());
                cmbDoctorID.setValue(appointment.getDoctorID());
                cmbTime.setValue(appointment.getTime());
                cmbStatus.setValue(appointment.getStatus());

                if (appointment.getDate() != null) {
                    dtpDate.setValue(LocalDate.ofEpochDay(appointment.getDate().toEpochDay()));
                } else {
                    dtpDate.setValue(null);
                }

            }
        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occur!"+e.getMessage()).show();
            reloadForm();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (dtpDate.getValue() == null ||
                cmbTime.getValue() == null ||
                cmbStatus.getValue() == null) {

            new Alert(Alert.AlertType.WARNING, "Please fill in all fields before updating an appointment.").show();
            return;
        }
        try {
            boolean isUpdate = appointmentController.UpdateAppointment(txtId.getText(), dtpDate.getValue(), cmbTime.getValue().toString(), cmbStatus.getValue().toString());
            if (isUpdate) new Alert(Alert.AlertType.INFORMATION, "Update Successful!").show();
            else new Alert(Alert.AlertType.ERROR, "Unsuccessful!").show();
        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
        reloadForm();
    }


}
