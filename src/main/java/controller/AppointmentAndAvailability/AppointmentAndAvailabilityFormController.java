package controller.AppointmentAndAvailability;

import controller.Appointment.AppointmentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentAndAvailabilityFormController implements Initializable {

    @FXML
    private TableColumn clmDate;

    @FXML
    private TableColumn clmDoctorID;

    @FXML
    private TableColumn clmID;

    @FXML
    private TableColumn clmPatientID;

    @FXML
    private TableColumn clmSatus;

    @FXML
    private TableColumn clmTime;

    @FXML
    private ComboBox cmbStatus;

    @FXML
    private TableView tblAppointments;

    @FXML
    private TextField txtId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }
    private void loadTable(){

        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        clmDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        clmSatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        AppointmentController appointmentController = new AppointmentController();
        List<Appointment> all = appointmentController.getAll();
        ObservableList<Appointment> observableList = FXCollections.observableArrayList();

        all.forEach(appointment -> observableList.add(appointment));
        tblAppointments.setItems(observableList);
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }


}
