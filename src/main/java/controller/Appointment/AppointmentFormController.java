package controller.Appointment;

import controller.Patient.PatientController;
import controller.doctor.DoctorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Doctor;
import model.Patient;

import java.net.URL;
import java.sql.SQLException;
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
        loadTable();
        loadTime();
        loadStatus();
        loadPatientsID();
        loadDoctorsID();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        appointmentController.addAppointment(new Appointment(
                genarateID(),
                cmbPatientID.getValue().toString(),

        ));

        reloadForm();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        reloadForm();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        reloadForm();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        reloadForm();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        reloadForm();
    }


}
