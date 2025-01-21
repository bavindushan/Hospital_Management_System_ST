package controller.doctor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorFormController implements Initializable {

    @FXML
    private CheckBox cbAvilability;

    @FXML
    private TableColumn clmEmail;

    @FXML
    private TableColumn clmID;

    @FXML
    private TableColumn clmName;

    @FXML
    private TableColumn clmPassword;

    @FXML
    private ComboBox cmbQualification;

    @FXML
    private ComboBox cmbSpeciality;

    @FXML
    private TableView tblAdminTable;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtTelNo;

    DoctorController doctorController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSpeciality();
        loadQualifications();
        doctorController = new DoctorController();
        loadTable();
    }
    private void loadSpeciality(){
        ObservableList<String> specialityList = FXCollections.observableArrayList();
        specialityList.add("Cardiology");
        specialityList.add("Neurology");
        specialityList.add("Pediatrics");
        specialityList.add("Orthopedics");
        specialityList.add("Dermatology");
        cmbSpeciality.setItems(specialityList);
    }
    private void loadQualifications(){
        ObservableList<String> qualificationList = FXCollections.observableArrayList();
        qualificationList.add("MBBS, MD ");
        qualificationList.add("MBBS, DM");
        qualificationList.add("MBBS, DCH");
        qualificationList.add("MBBS, MS");
        qualificationList.add("MBBS, DD");
        cmbQualification.setItems(qualificationList);
    }
    private void loadTable(){

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
