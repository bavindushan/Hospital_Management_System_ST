package controller.Report;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Admin;
import model.Report;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.custom.impl.AdminBoImpl;
import service.custom.impl.ReportBoImpl;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {

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

    private AdminBoImpl adminBo;
    private ReportBoImpl reportBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminBo = new AdminBoImpl();
        reportBo  = new ReportBoImpl();
        loadTable();
        loadAdminId();
        loadFormat();
        txtId.setText(generateID());
    }
    private String generateID(){
        String lastId = reportBo.getLastId();

        if (lastId==null) return "R001";

        int numericPart = Integer.parseInt(lastId.substring(1));
        int newNumber = numericPart+1;

        return String.format("R%03d",newNumber);
    }
    private void loadFormat(){
        cmbFormat.setValue("PDF");
    }
    private void loadAdminId(){
        List<Admin> adminList = adminBo.getAllAdmin();
        ObservableList<String> observableList = FXCollections.observableArrayList();

        adminList.forEach(admin -> observableList.add(admin.getAdminID()));
        cmbDoctorID.setItems(observableList);
    }
    private void loadTable(){
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmAdminId.setCellValueFactory(new PropertyValueFactory<>("adminId"));
        clmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmFormat.setCellValueFactory(new PropertyValueFactory<>("format"));

        try {
            List<Report> all = reportBo.getAll();
            ObservableList<Report> observableList = FXCollections.observableArrayList(all);
            tblReports.setItems(observableList);

        } catch (SQLException e) {
            System.out.println("An Error Occur!"+e.getMessage());
        }
    }

    @FXML
    void btnGenarateReportOnAction(ActionEvent event) {

        if (txtId.getText().isEmpty()||cmbDoctorID.getValue()==null||dtpDate.getValue()==null) return;

        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/bill_Report.jrxml");


            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());


            //  Get the user's Downloads folder path
            String userHome = System.getProperty("user.home");
            String downloadPath = userHome + "/Downloads/bill_summary.pdf"; // Save in to download file

            JasperExportManager.exportReportToPdfFile(jasperPrint,downloadPath);

            new Alert(Alert.AlertType.INFORMATION, "Download Complete in to your download file!").show();


            reportBo.add(new Report(
                    txtId.getText(),
                    cmbDoctorID.getValue().toString(),
                    "Billing Summary",
                    dtpDate.getValue(),
                    "PDF"
            ));
        } catch (JRException | SQLException e) {
            System.out.println("An Error occur!"+e.getMessage());

        }finally {
            loadTable();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadAdminId();
        loadTable();
    }

}
