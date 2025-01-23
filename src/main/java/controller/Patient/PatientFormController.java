package controller.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
        loadTable();
        txtId.setText(genaratePatientId());
    }
    private void loadTable(){

        try {
            clmID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            clmName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            clmAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
            clmGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            clmTel.setCellValueFactory(new PropertyValueFactory<>("TelNo"));
            clmMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("MedicalHistory"));

            ObservableList<Patient> observableList = FXCollections.observableArrayList();
            List<Patient> patientList = patientController.getAll();

            patientList.forEach(patient -> observableList.add(patient));

            tblPatientTable.setItems(observableList);

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
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
    private String genaratePatientId(){
        try {
            String lastID = patientController.getLastID();
            if (lastID==null) return "P001";

            int numericPart = Integer.parseInt(lastID.substring(1));
            int newNumber = numericPart + 1;

            return String.format("P%03d",newNumber);


        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
            return "P001";
        }
    }

    private void reloadTextBox(){
        txtId.setText(genaratePatientId());
        txtName.setText("");
        txtAge.setText("");
        txtTelNo.setText("");
        loadGender();
        loadMedicalHistory();
        loadTable();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {

            if (txtId.getText().isEmpty()||txtName.getText().isEmpty()||txtAge.getText().isEmpty()||
                    cmbGender.getValue().toString().isEmpty()||txtTelNo.getText().isEmpty()){
                new Alert(Alert.AlertType.WARNING,"Please fill all fields.").show();
                return;
            }

            boolean isAdd = patientController.addPatient(new Patient(
                    txtId.getText(),
                    txtName.getText(),
                    txtAge.getText(),
                    cmbGender.getValue().toString(),
                    txtTelNo.getText(),
                    cmbMedicalHistory.getValue().toString())
            );

            if (isAdd) new Alert(Alert.AlertType.INFORMATION,"Patient added successful!").show();
            else new Alert(Alert.AlertType.ERROR,"Unsuccessful!");

            loadTable();
            reloadTextBox();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDelete = patientController.deletePatient(txtTelNo.getText());
            if (isDelete) new Alert(Alert.AlertType.INFORMATION,"Successful!");
            else new Alert(Alert.AlertType.ERROR,"Unsuccessful!");

            loadTable();
            reloadTextBox();

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }


}
