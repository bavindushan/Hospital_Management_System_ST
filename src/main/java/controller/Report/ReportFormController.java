package controller.Report;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    }
    private void loadTable(){
        try {
            clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmAdminId.setCellValueFactory(new PropertyValueFactory<>("admin_id"));
            clmType.setCellValueFactory(new PropertyValueFactory<>("report_type"));
            clmDate.setCellValueFactory(new PropertyValueFactory<>("generated_date"));
            clmFormat.setCellValueFactory(new PropertyValueFactory<>("exported_format"));

            List<Report> all = reportBo.getAll();
            ObservableList<Report> observableList = FXCollections.observableArrayList();

            all.forEach(report -> observableList.add(report));
            tblReports.setItems(observableList);
        } catch (SQLException e) {
            System.out.println("An Error Occur!"+e.getMessage());
        }
    }

    @FXML
    void btnGenarateReportOnAction(ActionEvent event) {

        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/bill_Report.jrxml");


            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());


            //  Get the user's Downloads folder path
            String userHome = System.getProperty("user.home");
            String downloadPath = userHome + "/Downloads/bill_summary.pdf"; // Save in to download file

            JasperExportManager.exportReportToPdfFile(jasperPrint,downloadPath);

            new Alert(Alert.AlertType.INFORMATION, "Download Complete in to your download file!").show();

//            JasperViewer.viewReport(jasperPrint,false//exitOnClose);  // if you want to view, then use this

        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

}
