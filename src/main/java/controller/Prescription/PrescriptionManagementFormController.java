package controller.Prescription;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrescriptionManagementFormController {

    @FXML
    private TableColumn<?, ?> clmadditionalnote;

    @FXML
    private TableColumn<?, ?> clmdoctorId;

    @FXML
    private TableColumn<?, ?> clmdosage;

    @FXML
    private TableColumn<?, ?> clmduration;

    @FXML
    private TableColumn<?, ?> clmid;

    @FXML
    private TableColumn<?, ?> clmmedicaldetails;

    @FXML
    private TableColumn<?, ?> clmpatientId;

    @FXML
    private ComboBox<?> cmbdoctorId;

    @FXML
    private ComboBox<?> cmbpatientId;

    @FXML
    private TableView<?> tblprescriptions;

    @FXML
    private TextField txtAdditionalNotes;

    @FXML
    private TextField txtDosage;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMedicalDetails;

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
