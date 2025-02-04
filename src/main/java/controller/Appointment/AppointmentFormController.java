package controller.Appointment;

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

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTime();
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
