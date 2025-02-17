package controller.Report;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ReportFormController {

    @FXML
    private TableColumn clmAdminId;

    @FXML
    private TableColumn clmDate;

    @FXML
    private TableColumn clmFormat;

    @FXML
    private TableColumn clmId;

    @FXML
    private TableColumn clmType;

    @FXML
    private ComboBox cmbDoctorID;

    @FXML
    private ComboBox cmbFormat;

    @FXML
    private ComboBox cmbReportType;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TableView tblReports;

    @FXML
    private TextField txtId;

    @FXML
    void btnGenarateReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

}
