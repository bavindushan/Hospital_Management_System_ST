package controller.Report;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;

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

        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/bill_Report.jrxml");

            // ----------If you wanna handle data in this you can use this code---------------------
//            JRDesignQuery jrDesignQuery = new JRDesignQuery();
//            jrDesignQuery.setText("SELECT * FROM billing WHERE bill_id='B001'");
//            design.setQuery(jrDesignQuery);

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
