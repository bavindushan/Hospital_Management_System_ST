package controller.BillingAndPayments;

import controller.Patient.PatientController;
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
import javafx.scene.control.cell.PropertyValueFactory;
import model.Patient;
import model.PaymentBill;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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

    BillingAndPaymentController billingAndPaymentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        billingAndPaymentController = new BillingAndPaymentController();
        loadTable();
        txtId.setText(genarateID());
        loadStatus();
        loadPatientID();

    }
    private void loadPatientID(){
        PatientController patientController = new PatientController();
        try {
            List<Patient> patientList = patientController.getAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();

            patientList.forEach(patient -> observableList.add(patient.getID()));
            cmbPatientID.setItems(observableList);
        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
    }
    private void loadTable(){

        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPationID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        clmInvoiceName.setCellValueFactory(new PropertyValueFactory<>("invoiceName"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        List<PaymentBill> all = billingAndPaymentController.getAll();
        if (all == null) {
            System.out.println("Error: getAll() returned null!");
            return;
        }
        ObservableList<PaymentBill> observableList = FXCollections.observableArrayList();

        all.forEach(paymentBill -> observableList.add(paymentBill));
        tblBillsandPayment.setItems(observableList);
    }
    private String genarateID(){
        String lastId = billingAndPaymentController.getLastId();
        if (lastId==null) return "B001";

        int numericPart = Integer.parseInt(lastId.substring(1));
        int newnumber = numericPart+1;

        return String.format("B%03d",newnumber);
    }
    private void loadStatus(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Paid");
        observableList.add("Unpaid");
        cmbStatus.setItems(observableList);
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
