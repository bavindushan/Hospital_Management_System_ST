package controller.AppointmentAndAvailability;

import service.custom.impl.AppointmentBoImpl;
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
    AppointmentBoImpl appointmentBoImpl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentBoImpl = new AppointmentBoImpl();
        loadTable();
        loadStatus();
    }
    private void loadStatus(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Scheduled");
        observableList.add("Completed");
        observableList.add("Cancelled");

        cmbStatus.setItems(observableList);
    }
    private void loadTable(){

        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        clmDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        clmSatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        List<Appointment> all = appointmentBoImpl.getAll();
        ObservableList<Appointment> observableList = FXCollections.observableArrayList();

        all.forEach(appointment -> observableList.add(appointment));
        tblAppointments.setItems(observableList);
    }
    private void reloadForm(){
        loadTable();
        txtId.setText("");
        cmbStatus.setValue("");
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        reloadForm();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            Appointment appointment = appointmentBoImpl.searchAppointment(txtId.getText());

            if (appointment!=null){
                ObservableList<Appointment> observableList = FXCollections.observableArrayList(appointment);
                tblAppointments.setItems(observableList);
            }else {
                tblAppointments.getItems().clear();
                new Alert(Alert.AlertType.ERROR, "Not found!").show();
                reloadForm();
            }
        } catch (SQLException e) {
            System.out.println("A error couur"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "A error couur"+e.getMessage()).show();
            reloadForm();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if(!txtId.getText().isEmpty() && cmbStatus.getValue()!=null) {
                boolean isUpdate = appointmentBoImpl.UpdateStatus(txtId.getText(), cmbStatus.getValue().toString());
                if (isUpdate) new Alert(Alert.AlertType.INFORMATION, "Updated!").show();
                else new Alert(Alert.AlertType.ERROR, "Unsuccessful!").show();

            }else new Alert(Alert.AlertType.ERROR, "Fill all inputs!").show();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            new Alert(Alert.AlertType.ERROR, "An error occurred! " + e.getMessage()).show();
        }
        reloadForm();
    }


}
