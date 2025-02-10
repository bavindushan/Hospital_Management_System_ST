package controller.BillingAndPayments;

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

public class BillingAndPaymentFormController implements Initializable {

    @FXML
    private TableColumn clmDate;

    @FXML
    private TableColumn clmID;

    @FXML
    private TableColumn clmInvoiceName;

    @FXML
    private TableColumn clmPationID;

    @FXML
    private TableColumn clmStatus;

    @FXML
    private TableColumn clmTotal;

    @FXML
    private ComboBox cmbPatientID;

    @FXML
    private ComboBox cmbStatus;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TableView tblBillsandPayment;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtInvoiceName;

    @FXML
    private TextField txtTotalAmount;

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
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }


}
