package controller.Room;

import controller.Patient.PatientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Patient;
import model.assignRoom;

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
        txtId.setText(genarateID());
        txtAvilableBedsCount.setText("0");

        //add event listner to catch cmb box data
        cmbRoomType.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue!=null) txtAvilableBedsCount.setText(availableBedCount(newValue.toString()));
        });
    }
    private String availableBedCount(String roomType){
        try {
            if (roomType!=null) return String.valueOf(roomController.availableBedCount(roomType));
            else return "0";
        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            return "0";
        }
    }
    private String genarateID(){
        try {
            String lastId = roomController.lastID();
            if (lastId == null) return "RM001";

            int numericPart = Integer.parseInt(lastId.substring(2));
            int newNumwericPart = numericPart+1;

            return String.format("RM%03d",newNumwericPart);

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            return "RM001";
        }
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

            List<assignRoom> list = roomController.getAll();
            ObservableList<assignRoom> observableList = FXCollections.observableArrayList();

            list.forEach(assignRoom -> observableList.add(assignRoom));
            tblRoomTable.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            boolean isAdd = roomController.addRoom(new assignRoom(
                    genarateID(),
                    cmbPatientID.getValue().toString(),
                    cmbRoomType.getValue().toString()
            ));

            if (isAdd) new Alert(Alert.AlertType.INFORMATION,"Successful!");
            else new Alert(Alert.AlertType.ERROR,"Unsuccessful!");

            loadTable();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "This patient already Admitted!").show();
            System.out.println("An error occur ! "+e.getMessage());
            new Alert(Alert.AlertType.ERROR,"Unsuccessful!");
        }
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
