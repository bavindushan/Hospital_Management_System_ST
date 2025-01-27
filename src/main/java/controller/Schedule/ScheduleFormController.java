package controller.Schedule;

import controller.Staff.StaffController;
import controller.doctor.DoctorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Doctor;
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

    ScheduleServices scheduleServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scheduleServices = new ScheduleController();
        loadDoctorId();
        loadStaffId();
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
