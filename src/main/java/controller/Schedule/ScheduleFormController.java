package controller.Schedule;

import controller.Staff.StaffController;
import controller.doctor.DoctorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Doctor;
import model.Schedule;
import model.Staff;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ScheduleFormController implements Initializable {

    @FXML
    private TableColumn clmDoctorID;

    @FXML
    private TableColumn clmScheduleDetails;

    @FXML
    private TableColumn clmStaffID;

    @FXML
    private TableColumn clmscheduleID;

    @FXML
    private ComboBox cmbDoctorID;

    @FXML
    private ComboBox cmbSchedulesList;

    @FXML
    private ComboBox cmbStaffID;

    @FXML
    private TableView tblScheduleManagemenet;

    @FXML
    private TextField txtId;

    ScheduleController scheduleController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scheduleController = new ScheduleController();
        loadDoctorId();
        loadStaffId();
        loadScheduleList();
        txtId.setText(genarateID());
        loadTable();
    }
    private void loadTable(){

        clmscheduleID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        clmStaffID.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        clmScheduleDetails.setCellValueFactory(new PropertyValueFactory<>("scheduleDetails"));

        List<Schedule> all = scheduleController.getAll();
        ObservableList<Schedule> observableList = FXCollections.observableArrayList();

        all.forEach(schedule -> observableList.add(schedule));
        tblScheduleManagemenet.setItems(observableList);
    }
    private String genarateID(){
        String lastID = scheduleController.getLastID();
        if (lastID==null) return "S001";

        int numericPart = Integer.parseInt(lastID.substring(1));
        int newIDNumber = numericPart + 1;
        return String.format("S%03d",newIDNumber);
    }
    private void loadScheduleList(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Morning Shift: 9 AM to 1 PM");
        observableList.add("Afternoon Shift: 1 PM to 5 PM");
        observableList.add("Evening Shift: 5 PM to 9 PM");
        observableList.add("Night Shift: 9 PM to 1 AM");
        observableList.add("Flexible Hours");

        cmbSchedulesList.setItems(observableList);
    }
    private void loadStaffId(){
        StaffController staffController = new StaffController();
        List<Staff> all = staffController.getAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();

        all.forEach(staff -> observableList.add(staff.getStaffID()));

        cmbStaffID.setItems(observableList);
    }
    private void loadDoctorId(){
        DoctorController doctorController = new DoctorController();
        try {
            List<Doctor> doctorList = doctorController.getAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();

            doctorList.forEach(doctor -> observableList.add(doctor.getId()));
            cmbDoctorID.setItems(observableList);

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            boolean isAdded = scheduleController.assignSchedule(new Schedule(
                    genarateID(),
                    cmbDoctorID.getValue().toString(),
                    cmbStaffID.getValue().toString(),
                    cmbSchedulesList.getValue().toString()
            ));
            if (isAdded) new Alert(Alert.AlertType.INFORMATION, "Added Successful!").show();
            else new Alert(Alert.AlertType.ERROR, "Unsuccessful!").show();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
        reloadForm();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDelete = scheduleController.deleteSchedule(txtId.getText());
            if (isDelete) new Alert(Alert.AlertType.INFORMATION,"Deleted!").show();
            else new Alert(Alert.AlertType.ERROR,"Not delete!").show();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
        reloadForm();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        reloadForm();
    }
    private void reloadForm(){
        loadTable();
        loadDoctorId();
        loadStaffId();
        loadScheduleList();
        txtId.setText(genarateID());
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            Schedule schedule = scheduleController.searchSchedule(txtId.getText());
            //set form
            txtId.setText(schedule.getId());
            cmbDoctorID.setItems(FXCollections.observableArrayList(schedule.getDoctorID()));
            cmbStaffID.setItems(FXCollections.observableArrayList(schedule.getStaffID()));
            cmbDoctorID.setItems(FXCollections.observableArrayList(schedule.getDoctorID()));
            cmbSchedulesList.setItems(FXCollections.observableArrayList(schedule.getScheduleDetails()));

            //set Table
            tblScheduleManagemenet.setItems(FXCollections.observableArrayList(schedule));

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            boolean isUpdate = scheduleController.updateSchedule(new Schedule(
                    txtId.getText(),
                    cmbDoctorID.getValue().toString(),
                    cmbStaffID.getValue().toString(),
                    cmbSchedulesList.getValue().toString()
            ));
            if (isUpdate) new Alert(Alert.AlertType.INFORMATION, "Update Successful!").show();
            else new Alert(Alert.AlertType.ERROR, "Unsuccessful!").show();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
    }
}
