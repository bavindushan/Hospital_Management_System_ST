package controller.Room;

import controller.Patient.PatientController;
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
import model.Patient;
import model.Room;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {

    @FXML
    private TableColumn clmAvilableBeds;

    @FXML
    private TableColumn clmBedCount;

    @FXML
    private TableColumn clmPatientID;

    @FXML
    private TableColumn clmRoomId;

    @FXML
    private TableColumn clmRoomType;

    @FXML
    private ComboBox cmbPatientID;

    @FXML
    private ComboBox cmbRoomType;

    @FXML
    private TableView tblRoomTable;

    @FXML
    private TextField txtAvilableBedsCount;

    @FXML
    private TextField txtBedsCount;

    @FXML
    private TextField txtId;

    RoomController roomController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomController = new RoomController();
        loadTable();
        loadPatientID();
        loadRoomTypes();
    }
    private void loadRoomTypes(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("General");
        observableList.add("ICU");
        observableList.add("Private");
        observableList.add("Semi-Private");

        cmbRoomType.setItems(observableList);
    }
    private void loadPatientID(){
        try {
            PatientController patientController = new PatientController();

            List<Patient> patientList = patientController.getAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            patientList.forEach(patient -> observableList.add(patient.getID()));

            cmbPatientID.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTable(){
        try {

            clmRoomId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmPatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            clmRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
            clmAvilableBeds.setCellValueFactory(new PropertyValueFactory<>("availableBeds"));
            clmBedCount.setCellValueFactory(new PropertyValueFactory<>("bedsCount"));

            List<Room> list = roomController.getAll();
            ObservableList<Room> observableList = FXCollections.observableArrayList();

            list.forEach(room -> observableList.add(room));
            tblRoomTable.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
