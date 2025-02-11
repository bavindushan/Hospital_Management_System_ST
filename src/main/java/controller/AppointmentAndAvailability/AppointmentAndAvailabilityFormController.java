package controller.AppointmentAndAvailability;

import controller.Appointment.AppointmentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.net.URL;
import java.sql.SQLException;
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
    AppointmentController appointmentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentController = new AppointmentController();
        loadTable();

    }
    private void loadTable(){

        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        clmDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        clmSatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        List<Appointment> all = appointmentController.getAll();
        ObservableList<Appointment> observableList = FXCollections.observableArrayList();

        all.forEach(appointment -> observableList.add(appointment));
        tblAppointments.setItems(observableList);
    }
    private void reloadForm(){
        loadTable();
        txtId.setText("");
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
                ObservableList<Appointment> observableList = FXCollections.observableArrayList(appointment);
                tblAppointments.setItems(observableList);
            }else {
                tblAppointments.getItems().clear();
                new Alert(Alert.AlertType.ERROR, "Not found!").show();
            }
        } catch (SQLException e) {
            System.out.println("A error couur"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "A error couur"+e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }


}
