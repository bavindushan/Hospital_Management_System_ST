package controller.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientFormController implements Initializable {

    @FXML
    private TableColumn clmAge;

    @FXML
    private TableColumn clmGender;

    @FXML
    private TableColumn clmID;

    @FXML
    private TableColumn clmMedicalHistory;

    @FXML
    private TableColumn clmName;

    @FXML
    private TableColumn clmTel;

    @FXML
    private ComboBox cmbGender;

    @FXML
    private ComboBox cmbMedicalHistory;

    @FXML
    private TableView tblPatientTable;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTelNo;

    PatientController patientController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientController = new PatientController();
        loadGender();
        loadMedicalHistory();
    }
    private void loadGender(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Male");
        observableList.add("Female");
        observableList.add("Other");

        cmbGender.setItems(observableList);
    }
    private void loadMedicalHistory(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Cardiovascular Diseases");
        observableList.add("Respiratory Conditions");
        observableList.add("Metabolic Disorders");
        observableList.add("Neurological Disorders ");
        observableList.add("Mental Health Conditions");
        observableList.add("Autoimmune and Inflammatory Diseases");
        observableList.add("Infectious Diseases");
        observableList.add("Gastrointestinal Disorders");
        observableList.add("Cancer");
        observableList.add("Chronic Pain and Musculoskeletal Disorders");

        cmbMedicalHistory.setItems(observableList);

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
