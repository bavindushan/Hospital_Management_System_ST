package controller.Patient;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
